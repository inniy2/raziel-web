insert into mysql_host (mysql_host_name, cluster_name, host_type) values('test-bae-client1.testdb','test-cluster',3);
insert into mysql_host (mysql_host_name, cluster_name, host_type) values('test-bae-client2.testdb','test-cluster',2);
insert into mysql_host (mysql_host_name, cluster_name, host_type) values('test-bae-client3.testdb','test-cluster',1);


insert into raziel_user (razie_user, raziel_password) values('sangsun.bae@bolt.eu','1234');


insert into alter_status (
cluster_name,
table_schema,
table_name,
ghost_host_name,
-- check_replica_list,
-- alter_statement,
register_email,
register_timestamp ) values (
'dummy-cluster',
'dummy-database',
'dummy_tbl1',
'dummy-bae-client1.testdb',
-- '',
-- '',
'dummy@bolt.eu',
'2019-11-07 12:00:00'
);

insert into alter_history (
id,
cluster_name,
table_schema,
table_name,
ghost_host_name,
-- check_replica_list,
-- alter_statement,
register_email,
register_timestamp,
create_timestamp
) values (
100000000,
'dummy-cluster',
'dummy-database',
'dummy_tbl1',
'dummy-bae-client1.testdb',
-- '',
-- '',
'dummy@bolt.eu',
'2019-11-07 12:00:00',
'2019-11-08 13:00:00'
);