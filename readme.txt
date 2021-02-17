Для инициализации БД нужно:

*********************************************************************

1. Запустить сервер MySQL.

2. Зайти в административную консоль и выполнить команду

	source ABSOLUTE_PATH_TO_SCRIPT;

где ABSOLUTE_PATH_TO_SCRIPT - абсолютный путь к файлу:

	sql/dbcreate-mysql.sql

3. Создать пользователя с именем root, паролем #Sugonyako90 и дать ему все права на базу данных conferencesdb:

	grant all privileges on conferencesdb.* to root@'%' identified by '#Sugonyako90';	
	
(выполнить в административной консоли MySQL)

4. Открыть проект в Eclipse.

5. Сконфигурировать в Eclipse Apache Tomcat.
