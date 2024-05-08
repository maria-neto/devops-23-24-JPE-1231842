# Class Assignment 3, Part 2: Virtualization with Vagrant

## Introduction

For the second part of this class assignment, the main goal is to understand and apply the concepts of virtualization using Vagrant.
Vagrant is a tool that allows the user to create and configure lightweight, reproducible, and portable development environments. Vagrant uses a simple configuration
file to define the software and resources needed to create a virtual machine. The user can use the same configuration file to create the same environment on different
machines, making it easier to share and collaborate on projects.

Whereas in the first part of this assignment VirtualBox was used to create a virtual machine, but all the required configurations, as well as software download and installation
had to be done manually in order to run the projects from previous assignments, in this part of the assignment, with the use of Vagrant, it will be possible to automate the process
of creating and configuring the virtual machine, downloading all the required software and cloning the project repository, making it easier to manage and share the  development environment.

## Creating a Vagrantfile

A Vagrant file is a configuration file that defines the software and resources needed to create and configure a virtual machine. The Vagrant file is written in Ruby and uses a simple syntax
to define the configuration settings. The Vagrant file can be used to create a new virtual machine, configure the network settings, install software packages, and run scripts on the virtual machine.

For this assignment, a Vagrant file was developed based on the provided template from this [repository](https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/).


 ### 1. Vagrant Configuration

 The Vagrant file starts by defining the Vagrant configuration settings, such as the version of Vagrant to use and encapsulates all the configurations for the virtual
 machines defined within it.

```ruby
Vagrant.configure("2") do |config|
config.vm.box = "ubuntu/focal64"
config.ssh.insert_key = false
```

 The `config.vm.box` setting defines the base box to use for the virtual machine. In this case, the `ubuntu/focal64` box was used, which is an official Ubuntu 20.04 LTS box. The `config.ssh.insert_key` setting is used 
 to disable the automatic insertion of the SSH key into the virtual machine. This setting is useful when using a custom SSH key for authentication, which is not being used in this assigment.
 

 ### 2. Common Provisioning 

 This section defines the common provisioning settings for all the virtual machines defined within the Vagrant file. 

```ruby
config.vm.provision "shell", inline: <<-SHELL
sudo apt-get update -y
sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
    openjdk-17-jdk-headless
# ifconfig
SHELL
```

 The `config.vm.provision` setting is used to define the provisioning settings for the virtual machine. In this case, a shell script is used to update the package list and install the necessary software packages.
 The `iputils-ping`, `avahi-daemon`, `libnss-mdns`, `unzip`, and `openjdk-17-jdk-headless` packages are installed to provide network utilities, mDNS support, and the Java Development Kit (JDK) for running Java applications.


 ### 3. Database Virtual Machine Configuration

 This section defines the configuration settings for the database virtual machine, including networking and provisioning.

```ruby
config.vm.define "db" do |db|
db.vm.box = "ubuntu/focal64"
db.vm.hostname = "db"
db.vm.network "private_network", ip: "192.168.56.11"
```
 
 The `db.vm.network` setting is used to define the networking settings for the virtual machine. In this case, a private network is created with the IP address 192.168.56.11.

 **3.1 Port Forwarding**

 This section defines the port forwarding settings for the database virtual machine, allowing access to the database service from the host machine.

```ruby
db.vm.network "forwarded_port", guest: 8082, host: 8082
db.vm.network "forwarded_port", guest: 9092, host: 9092
```
      
 The `db.vm.network` setting with the `forwarded_port` option is used to define the port forwarding settings for the virtual machine. In this case, the ports 8082 and 9092 are forwarded from the guest machine to the host machine.

 **3.2 Provisioning for the Database Virtual Machine**

 This section downloads H2 database and runs the H2 server process using Java, ensuring it runs continuously.

```ruby
db.vm.provision "shell", inline: <<-SHELL
wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
SHELL
```
        
 The `db.vm.provision` setting is used to define the provisioning settings for the virtual machine. In this case, a shell script is used to download the H2 database jar file.
 The `wget` command is used to download the H2 database jar file from the Maven repository.
 

 ### 4. WebServer Virtual Machine Configuration

This section defines settings specified for the webserver virtual machine, including networking, provider settings, port forwarding and provisioning.

```ruby
    config.vm.define "web" do |web|
    web.vm.box = "ubuntu/focal64"
    web.vm.hostname = "web"
    web.vm.network "private_network", ip: "192.168.56.10"
```

The `web.vm.network` setting is used to define the networking settings for the virtual machine. In this case, a private network is created with the IP address 192.168.56.10.
   
    
 **4.1 Allocating Resources**

This section defines the resource allocation settings for the webserver virtual machine, including the amount of memory to allocate.
            
```ruby
   web.vm.provider "virtualbox" do |v|
   v.memory = 1024
   end
```

The `web.vm.provider` setting is used to define the provider-specific settings for the virtual machine. In this case, the `virtualbox` provider is used to define the memory allocation settings. The `vb.memory` setting is used to define the amount of memory to allocate to the virtual machine. In this case, 1024 MB of memory was allocated.

 **4.2 Port Forwarding**

This section defines the port forwarding settings for the webserver virtual machine, allowing access to the web server from the host machine.

```ruby
web.vm.network "forwarded_port", guest: 8080, host: 8080
```

The `web.vm.network` setting with the `forwarded_port` option is used to define the port forwarding settings for the virtual machine. In this case, the port 8080 is forwarded from the guest machine to the host machine.

 **4.3 Provisioning for the WebServer Virtual Machine**

 ```ruby
  web.vm.provision "shell", inline: <<-SHELL, privileged: false
  # sudo apt-get install git -y
  # sudo apt-get install nodejs -y
  # sudo apt-get install npm -y
  # sudo ln -s /usr/bin/nodejs /usr/bin/node
  sudo apt install -y tomcat9 tomcat9-admin
  # If you want to access Tomcat admin web page do the following:
  # Edit /etc/tomcat9/tomcat-users.xml
  # uncomment tomcat-users and add manager-gui to tomcat user

  # Change the following command to clone your own repository!
  git clone https://github.com/maria-neto/devops-23-24-JPE-1231842.git
  cd devops-23-24-JPE-1231842/CA2/Part2
  chmod u+x gradlew
  ./gradlew clean build
  timeout 2m ./gradlew bootRun
  # To deploy the war file to tomcat9 do the following command:
  # sudo cp ./build/libs/basic-0.0.1-SNAPSHOT.war /var/lib/tomcat9/webapps
  SHELL
  end
 ```

In this section, the provisioning settings for the webserver virtual machine are defined. A shell script is used to install the Tomcat server, clone the project repository, build the project, and run the web server. 
The `sudo apt install -y tomcat9 tomcat9-admin` command is used to install the Tomcat server and the Tomcat admin package.
The `git clone` command is used to clone the project repository from GitHub. 
The `./gradlew clean build` command is used to build the project using the Gradle Wrapper. 
The `timeout 2m ./gradlew bootRun` command is used to run the web server using the Gradle Wrapper. 
The `timeout` command is used to set a time limit for the execution of the command, ensuring it stops running after 2 minutes.

 ### 5. Running the Virtual Machines

 After defining the configuration settings in the Vagrant file, the virtual machines can be created and provisioned using the `vagrant up` command.
 The `vagrant up` command reads the Vagrant file, creates the virtual machines, and runs the provisioning scripts to install the necessary software packages and configure the virtual machines.
 Once the virtual machines are created and provisioned, the user can access the web server by opening a browser and navigating to the IP address of the web server virtual machine followed by the port number 8080.
 The user can also access the H2 database console by opening a browser and navigating to the IP address of the database virtual machine followed by the port number 8082 or 9092.





        
      
    
    