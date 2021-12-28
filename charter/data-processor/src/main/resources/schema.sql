CREATE TABLE if not exists CUSTOMER_BILL_DATA(
	ID serial primary key,
	ACCOUNT_NUMBER varchar(100),
	DIVISION_ID varchar(50),
	CUSTOMER_TYPE varchar(10),
	PHONE_NUMBER varchar(50),
	EMAIL varchar(50),
	SMS_NUMBER varchar(50),
	IVR_NUMBER varchar(50),
	ADDRESS_1 varchar(255),
	ADDRESS_2 varchar(255),
	CITY varchar(50),
	STATE varchar(50),
	ZIP varchar(50),
	NAME varchar(100),
	DUE_DATE varchar(50),
	LST_STMT_BAL varchar(50),
	E_BILL varchar(5)
);

CREATE TABLE IF NOT EXISTS CAMPAIGN(
  ID serial primary key,
  TASK_NAME varchar(100),
  TYPE varchar(50),
  CRON_EXPRESSION varchar(100),
  MAPPING_FILE varchar(100),
  STATUS boolean
);