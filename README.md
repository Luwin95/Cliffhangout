# Cliffhangout

This project is a Java EE based web project made during Open Classrooms Java EE expert training

## Getting Started

To use this project you should first download or fork it.

### Prerequisites

The project needs the following prerequisites to be run :

```
Maven (v3.5)
JDK 8 and JRE
Tomcat Server (v9)
PostgreSQL Server
```

### Installing

####Database

Build Database : Use SQL scripts to create db ( you can find them in db folder of the project ). 

```
structure.sql* (contains db structure sql script)
content.sql* (contains datas needed to make the application work)
fixtures.sql (contains datas examples)
```
(*) Required to make the application work

Configure connection : copy the context.xml file in the db folder to cliffhangout-webapp/src/main/webapp/META-INF.
Edit created file with your db informations like following
```
name="jdbc/DB_CLIFFHANGOUT"
auth="Container"
type="javax.sql.DataSource"
url="URL TO YOUR DB"
driverClassName="org.postgresql.Driver"
username="YOUR DB USERNAME"
password="YOUR PASSWORD"
```
Your DB is now ready to work.

####Upload folder
Create an upload directory :  In order to allow users to upload photos and pdf on the website the project uses an external folder. 

To configure it you must create the properties file business.properties in resources of cliffhangout-business module under com.cliffhangout.business.config package.
Then copy and configure the following into the properties file you created.

```
com.cliffhangout.uploadDirectory = path to your upload folder
```
Add your upload directory to your config doing one of the following :
* In tomcat server.xml config file : Add the context to your tomcat/conf/server.xml file at the end of host appbase webapps config 

```
<Context docBase="path to your upload folder" path="/uploadCliffhangout" />
```

* In your IDE add deployment of the folder when running application or add the config above to your tomcat server and use server.xml config in your IDE

You can now use the application and login :

```
login : cliffhangout-admin
password : password
```
We strongly recommend to change the password during first connexion.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring](https://spring.io/) - JDBC, security and dependencies injection tools
* [Struts](https://struts.apache.org/) - MVC framework
* [PostgreSQL](https://www.postgresql.org/) - DataBase System
* [Bootstrap](https://getbootstrap.com/) - CSS responsive framework
* [JQuery](https://jquery.com/) - Javascript Framework
* [Tomcat](http://tomcat.apache.org/) - Web server



## Authors

* **Luwin 95** - *Initial work* - [Cliffhangout](https://github.com/Luwin95/Cliffhangout)
* See also the list of [contributors](https://github.com/Luwin95/Cliffhangout/graphs/contributors) who participated in this project.
