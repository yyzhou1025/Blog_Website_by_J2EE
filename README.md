# Blog_Website_by_J2EE

Blog_Website is a full-stack web application which users can login, create posts and other users can comments 

## Tech Stack

* MVC design pattern
* HTML/CSS/JavaScript/JAVA
* JSP/JSTL/ORM
* MySQL

### Prerequisites
* Apache Tomat v8.5
* Eclipse J2EE
* JAVA 8
* MySQL

### Installing

Clone the GitHub repository and then import Blog_Website_by_J2EE.war into your eclipse.

```
git clone https://github.com/yyzhou1025/Blog_Website_by_J2EE
```

To import WAR file into Eclipse J2EE, click on File -> Import. Select Web -> WAR File.

* **WAR** file: Provide the full pathname of the WAR file on your computer.
* **Web project**: This will pre-fill based on the WAR file name. You can change it, which is handy if
you’re experimenting.
* **Target runtime**: You will need to select “Apache Tomcat 8.5”. The first time you import a WAR
file (or create new “Dynamic Web Project”) you will need to declare the new runtime environment. Do this by clicking on “New” and filling in the form as follows:
	* **Apache Tomat v8.5**, then click “Next”
	* Provide the Tomcat installation directory by giving the full pathname of the directory
containing your unzipped version of Tomcat 8.5.
	* Click “Finished”.
* Click "Finished".

Run the imported project by “right-clicking” on the new project and selecting “Run As -> Run on Server. <br>
After Installing MySQL, create the anonymous account and the “test” database by using following code<br>
```
mysql -u root –p
alter user user() identified by '';
create user ''@'localhost' identified by password '';
grant all privileges on *.* to ''@'localhost';
create database test;
exit;
```

## Usage/Quick Start
Resister account, post blogs, comments other's blogs.


## Licenses
NAN

## Notes
You need to have Eclipse JEE and MySQL installed in order to run this project on local machine or you can click deployed link above to run it remotely.
