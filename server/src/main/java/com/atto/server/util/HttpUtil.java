package com.atto.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerMapping;

import com.atto.server.exception.NoTokenExistException;

/**
 * Created by dhjung on 2017. 8. 29..
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static final String ID_KEY = "id";
    public static final String PW_KEY = "pw";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_HEADER_VALUE_PREFIX = "Bearer";

    public static String getRequestUrl(HttpServletRequest request){
        return (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

    }

    public static JSONObject getRequestBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();

        JSONParser jparser = new JSONParser();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody = (JSONObject) jparser.parse(body);
        }catch (ParseException pe){
            pe.printStackTrace();
            logger.info("JSONParse Error from HttpRequest Body to JSON");
        }
        return jsonBody;
    }

    public static JSONObject getResponseBody(HttpServletRequest response) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = response.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();

        JSONParser jparser = new JSONParser();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody = (JSONObject) jparser.parse(body);
        }catch (ParseException pe){
            pe.printStackTrace();
            logger.info("JSONParse Error from HttpRequest Body to JSON");
        }
        return jsonBody;
    }

    public static String getTokenFromRequest(HttpServletRequest request) throws NoTokenExistException {

        String headerValue = request.getHeader(AUTH_HEADER);
        if (headerValue == null || headerValue.isEmpty()) {
            throw new NoTokenExistException("No " + AUTH_HEADER + " exist.");
        }

        headerValue = headerValue.trim();
        if (!headerValue.startsWith(AUTH_HEADER_VALUE_PREFIX)) {
            throw new NoTokenExistException("Header " + AUTH_HEADER + " value shoud start with " + AUTH_HEADER_VALUE_PREFIX + ".");
        }

        String[] values = headerValue.split(AUTH_HEADER_VALUE_PREFIX);

        if (values == null || values.length < 2) {
            throw new NoTokenExistException("Hearder " + AUTH_HEADER + "does not have a valid value.");
        }

        // only first element
        String token = values[1].trim();

        return token;
    }

    public static void addAutorizationHttpHeaderFromSecuirtyContextToHttpHeaders(HttpHeaders headers) {
        headers.add(AUTH_HEADER, getAuthorizationHttpHeaderValueFromSecuirtyContext());
    }

    public static void addAuthorizationHttpHeaderFromSecurityContextToHttpServletResponse(HttpServletResponse response) {
        response.addHeader(AUTH_HEADER, getAuthorizationHttpHeaderValueFromSecuirtyContext());
    }

    public static String getAuthorizationHttpHeaderValueFromSecuirtyContext() {
        return new String(AUTH_HEADER_VALUE_PREFIX + " " + SecurityContext.get().getToken());
    }

}
