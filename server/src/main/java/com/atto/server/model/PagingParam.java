package com.atto.server.model;

import static com.atto.server.constant.SecurityConstant.SQL_ORDER_ASC;
import static com.atto.server.constant.SecurityConstant.SQL_ORDER_DESC;
import static com.atto.server.constant.SecurityConstant.UNDERSCORE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagingParam {

    private Logger logger = LoggerFactory.getLogger(getClass());

    int size;
    int startIdx;
    String orderCol;
    String order;

    public PagingParam() {
    }

    public PagingParam(int size, int startIdx, String orderCol, String order) {
        this.size = size;
        this.startIdx = startIdx;
        this.orderCol = convertCamelCaseToUnderscore(orderCol);
        this.order = validateOrder(order);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public String getOrderCol() {
        return orderCol;
    }

    public void setOrderCol(String orderCol) {
        this.orderCol = convertCamelCaseToUnderscore(orderCol);
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = validateOrder(order);
    }

    @Override
    public String toString() {
        StringBuffer strbuf = new StringBuffer("{");
        strbuf.append("size: " + String.valueOf(size) + ", ");
        strbuf.append("startIdx: " + String.valueOf(startIdx) + ", ");
        strbuf.append("orderCol: " + orderCol + ", ");
        strbuf.append("order: " + order);
        strbuf.append("}");
        return strbuf.toString();
    }

    /* FIXME support idempotent */
    private static String convertCamelCaseToUnderscore(String camelCase) {
        // e.g., DEVICE_ID, DELIVERER without lowercase characters
        if (camelCase.contains(UNDERSCORE)) {
            return camelCase;
        } else if (isTextContainLowerCase(camelCase)) {
            return camelCase.replaceAll("(.)(\\p{Upper})", "$1_$2").toUpperCase();
        } else {
            return camelCase;
        }
    }

    public static boolean isTextContainLowerCase(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        return !text.equals(text.toUpperCase());
 }

    private static String validateOrder(String order) {
        String validOrder = SQL_ORDER_ASC;
        order = order.toUpperCase();

        switch(order) {
            case SQL_ORDER_ASC:
                validOrder = SQL_ORDER_ASC;
                break;
            case SQL_ORDER_DESC:
                validOrder = SQL_ORDER_DESC;
                break;
            default:
                validOrder = SQL_ORDER_ASC;
        }

        return validOrder;
    }

    public void validateOrderColumn(List<String> tableColumnNames) {
        logger.debug("validateOrderColumn pagingParam=" + toString());
        String requestOrderCoulmnName = getOrderCol();
        String validatedOrderColumnName = null;

        for (String tableName : tableColumnNames) {
            logger.trace("validateOrderColumn tableName: " + tableName);
            if (requestOrderCoulmnName.equalsIgnoreCase(tableName)) {
                validatedOrderColumnName = requestOrderCoulmnName;
                break;
            }
        }

        if (validatedOrderColumnName == null) {
            logger.warn("Ingore Invalid Order Column Request : " + requestOrderCoulmnName);
            validatedOrderColumnName = tableColumnNames.get(0); // first column name
        }

        logger.debug("validateOrderColumn validatedOrderColumnName:" + validatedOrderColumnName);
        setOrderCol(validatedOrderColumnName);
    }
}
