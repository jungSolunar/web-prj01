drop table if exists FT_USER;
drop table if exists FT_ROLE;
drop table if exists FT_USER_ROLE;
drop table if exists FT_MENU;
drop table if exists FT_ORDER;
drop table if exists FT_ORDER_MENU;
drop table if exists FT_IMG;
drop table if exists FT_MENU_IMG;
drop table if exists FT_STORE;

create table FT_STORE (
    STORE_SID VARCHAR(20) PRIMARY KEY NOT NULL,
    STORE_NAME VARCHAR(20) NOT NULL,
    USER_SID VARCHAR(20) NOT NULL,
    STORE_LOCATION VARCHAR(255)
);

insert into FT_STORE (STORE_SID, STORE_NAME, USER_SID)
values ('store-001', 'STORE1', 'user-002');

insert into FT_STORE (STORE_SID, STORE_NAME, USER_SID)
values ('store-002', 'STORE2', 'user-003');

create table FT_USER (
    USER_SID VARCHAR(20) PRIMARY KEY NOT NULL,
    USER_NAME VARCHAR(20) NOT NULL,
    LOGIN_TYPE VARCHAR(5) NOT NULL,
    USER_LOGIN_ID VARCHAR(20),
    USER_LOGIN_PW VARCHAR(20),
    KACCOUNT_EMAIL VARCHAR(100),
    KACCOUNT_SID VARCHAR(100)

);

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-001', 'admin', 'LOCAL', 'admin', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-002', 'store1.owner', 'LOCAL', 'owner01', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-003', 'store2.owner', 'LOCAL', 'owner02', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-004', 'fstv.manager01', 'LOCAL', 'fest01', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-005', 'fstv.manager02', 'LOCAL', 'fest02', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-006', 'user01', 'LOCAL', 'user01', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-007', 'user02', 'LOCAL', 'user02', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-008', 'user03', 'LOCAL', 'user03', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');

insert into (USER_SID, USER_NAME, LOGIN_TYPE, USER_LOGIN_ID, USER_LOGIN_PW)
values ('user-009', 'user04', 'LOCAL', 'user04', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImlhdCI6MCwiZXhwIjowfQ.JZNpwoNbvF-_WeuHtjQIHwCBNbJuobiM1ptCVqgrr7MGtEugejw9pcgsdLQg2ouiaHWTSEYwPwfxi5XsEZbneQ');


create table FT_ROLE (
    ROLE_SID VARCHAR(20) PRIMARY KEY NOT NULL,
    ROLE_NAME VARCHAR(20) NOT NULL,
    NOTE VARCHAR(255)
);

insert into (ROLE_SID, ROLE_NAME, NOTE)
values ('role-001', 'admin', 'System Administrator');

insert into (ROLE_SID, ROLE_NAME, NOTE)
values ('role-002', 'owner', 'Store Owner');

insert into (ROLE_SID, ROLE_NAME, NOTE)
values ('role-003', 'fstm', 'Festival Manager');

insert into (ROLE_SID, ROLE_NAME, NOTE)
values ('role-004', 'user', 'User');

create table FT_USER_ROLE (
    USER_SID VARCHAR(20) NOT NULL,
    ROLE_SID VARCHAR(20) NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (GROUP_UID, RSC_UID)
);

insert into (USER_SID, ROLE_SID)
values ('user-001', 'role-001');

insert into (USER_SID, ROLE_SID)
values ('user-002', 'role-002');

insert into (USER_SID, ROLE_SID)
values ('user-003', 'role-002');

insert into (USER_SID, ROLE_SID)
values ('user-004', 'role-003');

insert into (USER_SID, ROLE_SID)
values ('user-005', 'role-003');

insert into (USER_SID, ROLE_SID)
values ('user-006', 'role-004');

insert into (USER_SID, ROLE_SID)
values ('user-007', 'role-004');

insert into (USER_SID, ROLE_SID)
values ('user-008', 'role-004');

insert into (USER_SID, ROLE_SID)
values ('user-009', 'role-004');


create table FT_MENU (
    MENU_SID VARCHAR(20) PRIMARY KEY NOT NULL,
    STORE_SID VARCHAR(20) NOT NULL,
    MENU_NAME VARCHAR(20) NOT NULL,
    MENU_PRICE INT NOT NULL,
    MENU_IMG_SID VARCHAR(20),
    MENU_PRIORITY INT,
    MENU_STATE VARCHAR(4) NOT NULL,
    VISIBLE BIT NOT NULL,
    NOTE VARCHAR(255)
);

--STORE 1 MENU
insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-001', 'store-001', 'menu001', 10000, 'SOLD', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-002', 'store-001', 'menu002', 20000, 'SOLD', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-003', 'store-001', 'menu003', 15000, 'SOLD', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-004', 'store-001', 'menu004', 10000, 'SOUT', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-005', 'store-001', 'menu005', 10000, 'SOUT', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-006', 'store-001', 'menu006', 10000, 'SOLD', 0);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-007', 'store-001', 'menu007', 10000, 'SOLD', 0);

-- STORE 2 MENU
insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-011', 'store-002', 'menu011', 10100, 'SOLD', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-012', 'store-002', 'menu012', 20100, 'SOLD', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-013', 'store-002', 'menu013', 15100, 'SOLD', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-014', 'store-002', 'menu014', 10100, 'SOUT', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-015', 'store-002', 'menu015', 10100, 'SOUT', 1);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-016', 'store-002', 'menu016', 10100, 'SOLD', 0);

insert into FT_MENU(MENU_SID, STORE_SID, MENU_NAME, NENU_PRICE, MENU_STATE, VISIBLE)
values ('menu-017', 'store-002', 'menu017', 10100, 'SOLD', 0);

create table FT_ORDER (
    ORDER_SID VARCHAR(20) PRIMARY KEY NOT NULL,
    USER_SID VARCHAR(20) NOT NULL,
    STORE_SID VARCHAR(20) NOT NULL,
    PAYMENT VARCHAR(255) NOT NULL,
    PAY BIT NOT NULL,
    COMPLETE BIT NOT NULL,
    ORDER_DTM DTAETIME NOT NULL
);

insert insert into FT_ORDER (ORDER_SID, USER_SID, STORE_SID, PAYMENT, PAY, COMPLETE, ORDER_DTM)
values ('oreder-001', 'user-006', 'store-001', 'CARD', 1, 0, '2017-10-10 11:23:00');

insert insert into FT_ORDER (ORDER_SID, USER_SID, STORE_SID, PAYMENT, PAY, COMPLETE, ORDER_DTM)
values ('oreder-002', 'user-007', 'store-001', 'CARD', 1, 0, '2017-10-10 11:24:00');

insert insert into FT_ORDER (ORDER_SID, USER_SID, STORE_SID, PAYMENT, PAY, COMPLETE, ORDER_DTM)
values ('oreder-003', 'user-008', 'store-001', 'MONY', 0, 0, '2017-10-10 11:25:00');

insert insert into FT_ORDER (ORDER_SID, USER_SID, STORE_SID, PAYMENT, PAY, COMPLETE, ORDER_DTM)
values ('oreder-004', 'user-007', 'store-002', 'CARD', 1, 0, '2017-10-10 11:23:00');

insert insert into FT_ORDER (ORDER_SID, USER_SID, STORE_SID, PAYMENT, PAY, COMPLETE, ORDER_DTM)
values ('oreder-005', 'user-008', 'store-002', 'CARD', 1, 0, '2017-10-10 11:24:00');

insert insert into FT_ORDER (ORDER_SID, USER_SID, STORE_SID, PAYMENT, PAY, COMPLETE, ORDER_DTM)
values ('oreder-006', 'user-0089', 'store-002', 'MONY', 0, 0, '2017-10-10 11:25:00');


create table FT_ORDER_MENU (
    ORDER_SID VARCHAR(20) NOT NULL,
    MENU_SID VARCHAR(20) NOT NULL,
    MENU_COUNT INT NOT NULL,
    CONSTRAINT pk_order_menu PRIMARY KEY (ORDER_SID, MENU_SID)
);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-001', 'menu-001', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-001', 'menu-003', 2);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-001', 'menu-007', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-002', 'menu-002', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-002', 'menu-004', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-002', 'menu-005', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-003', 'menu-001', 5);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-003', 'menu-006', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-004', 'menu-011', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-004', 'menu-016', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-005', 'menu-013', 1);

insert into (ORDER_SID, MENU_SID, MENU_COUNT)
values ('order-006', 'menu-015', 1);


create table FT_IMG (
    IMG_SID VARCHAR(20) PRIMARY KEY NOT NULL,
    IMG_NAME VARCHAR(20) NOT NULL,
    IMG_TYPE VARCHAR(4),
    IMG_PATH VARCHAR(255)
);

create table FT_MENU_IMG (
    IMG_SID VARCHAR(20) NOT NULL,
    MENU_SID VARCHAR(20) NOT NULL,
    CONSTRAINT pk_menu_img PRIMARY KEY (IMG_SID, MENU_SID)
);