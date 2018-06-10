CREATE TABLE IF NOT EXISTS financial_asset
(
	id bigint auto_increment primary key,
	rate decimal(19,2) null,
	spread double null,
	symbol varchar(64) null,
	create_date datetime,
	update_date datetime,
	constraint symbol_uindex unique (symbol)
);


CREATE TABLE IF NOT EXISTS asset_seq
(
	next_val bigint null
);

CREATE TABLE IF NOT EXISTS trade
(
	id bigint auto_increment primary key,
	rate decimal(19,2) null,
	amount bigint,
	symbol varchar(64) null,
	type varchar(16) null,
	create_date datetime,
	update_date datetime
);


CREATE TABLE IF NOT EXISTS trade_seq
(
	next_val bigint null
);

