# Class Assignment 3, Part 1: Virtualization with Vagrant

## Introduction

Virtualization is a technology that allows the user to create multiple simulated environments or dedicated resources from a single, physical hardware system.
Virtualization is a way to separate the software from the hardware, allowing the user the possibility of running multiple operating systems on a single 
physical machine. Virtualization can be used to create a virtual machine (VM) that acts like a real computer with an operating system. The VM can be
used to run software that is not compatible with the host operating system or to test software in different environments.

In this assignment, the main goal is to understand and apply the concepts of virtualization, where the students will migrate and run the projects from 
previous assignments in a virtualized environment.

VirtualBox is a free, open-source virtualization software that allows the user to create and run virtual machines on a host operating system. VirtualBox can be used
to run multiple operating systems on a single physical machine, making it easier to test software in different environments.

## Creating a Virtual Machine
As this tutorial was applied to a Windows 11 machine, the first step was to download and install VirtualBox from the official [website](https://www.virtualbox.org/).
As this assignment aimed to replicate Ubuntu environments, the next step was to download the Ubuntu ISO image from the official [website](https://ubuntu.com/download/server).

After installing VirtualBox and downloading the Ubuntu ISO image, the next step was to create a new virtual machine. To create a new virtual machine, the user must open
VirtualBox and click on the "New" button. The user must then enter a name for the virtual machine, select the type of operating system, and select the version of the
operating system. The user must then select the amount of memory allocated to the VM (for optimal performance, at least 2048 MB should be allocated) and the size of the hard 
disk for the virtual machine (in this case, 25 GB were allocated). The user must then select the Ubuntu ISO image downloaded earlier as the installation media for the VM.

### Virtualization and Networking
 - Create a Host-Only Network
   - Open VirtualBox
   - Navigate to *File*, and then to *Host Network Manager*
   - Click on *Create* to add a new host-only network
   - Name and configure the network within the VirtualBox network settings
 
 - Set up networking in the VM
   - Open the VM settings in VirtualBox
   - Navigate to *Network*
   - Select *Network Adapter 2* to be a host-only adapter
   - Check the IP range of the host-only network in the VirtualBox network settings
   - Configure the network settings in the VM to use a static IP address within the host-only network range

### Setting up the Virtual Machine
 - Start the VM
 - Update the system
   - Run `sudo apt update` to update the package list
 - Install necessary network tools
   - Run `sudo apt install net-tools` to install the `net-tools` package
 - Configure the network
   - Run `sudo nano /etc/netplan/00-installer-config.yaml` to edit the network configuration file
   - Add the following configuration:
     ```yaml
     network:
       version: 2
       renderer: networkd
       ethernets:
         enp0s3:
           dhcp4: yes
         enp0s8:
           addresses: 
               - 192.168.56.5/24
     ```
   - Run `sudo netplan apply` to apply the network configuration

### SSH Setup
 - Install the OpenSSH server
   - Run `sudo apt install openssh-server` to install the OpenSSH server
 - Enable SSH password authentication
   - Edit the SSH configuration file by running `sudo nano /etc/ssh/sshd_config`
   - Change the *PasswordAuthentication* option to *yes*
   - Save the file and exit the editor
 - Restart the SSH service
   - Run `sudo service ssh restart` to restart the SSH service

### FTP Setup
 - Install the VSFTPD server
   - Run `sudo apt install vsftpd` to install the VSFTPD server
 - Edit the VSFTPD configuration file by running `sudo nano /etc/vsftpd.conf` to enable FTP write access
   - uncomment the line *write_enable=YES*
 - Restart the VSFTPD service
   - Run `sudo service vsftpd restart` to restart the VSFTPD service

## Software Installation
After setting up the virtual machine, the next step was to install the necessary software to run the projects from previous assignments.
The first step is to update the system before downloading any new software to the VM. To update the system, the user must run the following command:
```bash
sudo apt update
```

After updating the system, the next step was to install the necessary software to run the projects.
 1. Install Git in order to clone the project repositories:
    ```bash
    sudo apt install git
    ```
 2. Install Java Development Kit (JDK) to run Java applications:
    ```bash
    sudo apt install openjdk-17-jre
    ```
 The JDK version installed was OpenJDK 17, which is compatible with the projects from previous assignments, namely CA2 Part2.
 3. Install Maven to build and run Java projects:
    ```bash
    sudo apt install maven
    ```
 4. Install Gradle to build and run Java projects:
    ```bash
    sudo apt install gradle
    ```
In order to ensure the correct installation of the software, the user can run the following commands to check the installed versions:
```bash
git --version
java -version
mvn -version
gradle -version
```
After installing the required software, the user has gathered all the necessary conditions to run the projects from previous assignments in the virtualized environment.

## Clone the Repository
After installing the necessary software, the next step was to clone the project repository.
As the project's repository was still private, an SSH key was generated and added to the GitHub account to allow the user to clone the repository.
To generate the SSH key, the user must run the following command:
```bash
ssh-keygen -t rsa
cat ~/.ssh/id_rsa.pub
```
The user must then copy the generated SSH key and add it to the GitHub account.
After adding the SSH key to the GitHub account, the user can clone the project repository using the following command:
```bash
git clone git@github.com:maria-neto/devops-23-24-JPE-1231842.git
```
After cloning the repository, the user can navigate to the project directory and run the necessary commands to build and run the projects.

## Running the Projects
In order to run the projects from previous assignments, the first step was to configure Maven Wrapper and Gradle Wrapper to have executing permissions:
```bash
chmod +x mvnw
chmod +x gradlew
```

### CA1

 - Navigate to the CA1/basic directory
 - Run the following commands to build and run the project:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
 - To ensure the project is running correctly, the user can open a browser and navigate to VM's IP address followed by the port number 8080, in the following format:
    ```
   [VM IP address]:8080
   ```
   
### CA2 Part1

 - Navigate to the CA2/Part1 directory
 - Run the following commands to build and run the project:
   ```bash
   ./gradlew build
   ./gradlew runServer
   ```
 - To run the client, the user must open a new terminal in the localhost and run the following commands:
   ```bash
   ./gradlew build
   ./gradlew runClient --args="192.168.56.5 59001"
   ```
The arguments passed to the client are the IP address of the VM and the port number where the server is running.
 - The project should be running correctly, and the user should be able to enter a chatroom.

### CA2 Part2

 - Navigate to the CA2/Part2 directory
 - Run the following commands to build and run the project:
   ```bash
   ./gradlew build
   ./gradlew bootRun
   ```
 - To ensure the project is running correctly, the user can open a browser and navigate to VM's IP address followed by the port number 8080, in the following format:
    ```
   [VM IP address]:8080
   ```

## Conclusion
In conclusion, this assignment aimed to introduce the concepts of virtualization to migrate and run the projects from previous assignments in a virtualized environment. As such,
the user was able to create a virtual machine using VirtualBox, install the necessary software to run the projects, clone the project repository, and run the projects from 
previous assignments in the virtualized environment, allowing a better comprehension of virtualization concepts and technologies, as well as enhancing the capabilities to test software 
in different development environments.






 
