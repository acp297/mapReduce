# Hadoop Application for uploading image
#### ABOUT
 
Map reduce job to upload image to Imgur. 
This job will take csv file containing image urls as an input and will output Imgur image url.
This Application is deployed in local on mac  as well as in Cloudera Docker VM.

## Steps to run the job

### Prerequisite 
* It requires Mac Machine.
* Ensure Java is installed. JDK Version : 1.7
* Ensure Maven is installed.

### Deployment 
 * We can run this app in local machine by configuring Hadoop locally in the standalone mode.
 * Also we can use Cloudera Docker VM to run this hadoop job.

### Steps to configure Hadoop locally in the standalone mode
 * Install Hadoop
   * Download Hadoop-2.7.7 from [here](https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-2.7.7/hadoop-2.7.7.tar.gz).
   * Extract the contents of the zip to a folder of your choice.
 * Configure Hadoop
   * Navigate inside the folder(hadoop-2.7.7) which you got after unzipping. using **cd** command
   * Once you are inside hadoop-2.7.7 folder
   
     * Enter : ``vi etc/hadoop/hadoop-env.sh``
       * ADD ``export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home`` 
         * To get java path enter : ``/usr/libexec/java_home``
         
     * Enter : ``vi etc/hadoop/core-site.xml``
       * Add 
       ```xml
       <configuration>
           <property>
               <name>fs.defaultFS</name>
               <value>hdfs://localhost:9000</value>
           </property>
       </configuration>
       ```
     * Enter : ``vi etc/hadoop/hdfs-site.xml``
       * Add
       ```xml
       <configuration>
           <property>
               <name>dfs.replication</name>
               <value>1</value>
           </property>
       </configuration>
       ```
       
     * Enter : ``vi etc/hadoop/mapred-site.xml``
       * Add
       ```xml
       <configuration>
           <property>
               <name>mapreduce.framework.name</name>
               <value>yarn</value>
           </property>
       </configuration>
       ```
       
     * Enter : ``vi etc/hadoop/yarn-site.xml``
       * Add
       ```xml
       <configuration>
           <property>
               <name>yarn.nodemanager.aux-services</name>
               <value>mapreduce_shuffle</value>
           </property>
       </configuration>
       ```
 * Configure SSH in mac
    - go to System Preferences
    - go to Sharing
    - Then click on Remote Login to enable SSH
    
 * Enable password less login to SSH
    - Enter this in your terminal.
      - ``ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa``
      - ``cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys``
 
 * Verify the installation
   - Navigate Inside you hadoop-2.7.7 directory
   - First Format the filesystem
     * Enter ``bin/hdfs namenode -format``
   - Start NameNode daemon and DataNode daemon:
     * Enter ``sbin/start-dfs.sh ``
     * Enter ``sbin/start-yarn.sh``
     
     * Verify name node, data node, secondary node, resource manager are running using command: 
       ```jps```
     
     - Browse the web interface for the name node at - [http://localhost:50070/](http://localhost:50070/)  OR [http://localhost:9870](http://localhost:9870)
     - And resource manager at - [http://locbalhost:8088/](http://localhost:8088/)
   
   - Stop all daemons :
     * ``sbin/stop-all.sh``
     
     
#### Steps to Build the Jar
   1. Clone this repo.
      * Enter : `git clone {gitAddress}`
   2. Navigate inside the mapReduce folder using *cd* command.
      * Inside mapReduce folder Enter : 
      ```text
      mvn package
      ```
   3. mapReduce-1.0-SNAPSHOT.jar will get generated inside target folder.
   
### Steps to run the jar in hadoop env.
   - Ensure your nodes are running locally.
   - Move to Hadoop-2.7.7 folder 
   - Create a input folder inside HDFS.
     * Enter ``bin/hdfs dfs -mkdir /input``
   - Copy a sample csv file to the input folder in the HDFS from local file system.
     * Enter ``bin/hdfs dfs -put {fullPath}/sample.csv /input``
     * Verify if it copied : ``bin/hdfs dfs -ls`` or go to [http://localhost:50070/explorer.html#/](http://localhost:50070/explorer.html#/)
   - Submit the job 
      Enter : ```bin/hadoop jar /{fullPathOfTheJar}/mapReduce-1.0-SNAPSHOT.jar Driver /input/sample.csv /output```
      
      We will see map reduce job gets spawned in your terminal.
      Also we can monitor the job in yarn : [http://localhost:8088/cluster](http://localhost:8088/cluster)

#### Steps to configure Cloudera Docker Container 
 * Ensure you have jdk 1.7 installed in your system.
 * Ensure you have installed your docker and it is up and running.
 * Navigate inside the repo which you have cloned.
    - Enter: ``sh run.sh``
        1. It builds the jar.
        2. It builds the docker image.
        3. Runs the container.
    - Running first time it will take 30-60 mins depending upon internet speed.
  
 * Verify : [http://localhost:8888/](http://localhost:8888/)
    - username : cloudera
    - password : cloudera
    
 * Upload sample csv file using [http://localhost:8888/filebrowser/](http://localhost:8888/filebrowser/). We can browse local file system.
   It will put your sample csv file in '/user/cloudera/' directory of the container.
 
 * Submit the job inside container.
   - In the Container shell(root@quickstart) Enter : ``/usr/bin/hadoop jar /usr/local/mapReduce-1.0-SNAPSHOT.jar Driver /user/cloudera/sample.csv /output``
  
   

 


