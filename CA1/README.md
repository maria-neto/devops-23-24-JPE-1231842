# Class Assignment 1 : Version Control with Git

## Introduction: Version Control Systems

A version control system (VCS) is a tool that manages different versions of source code, recording 
changes made to a file or set of files over time so that it can be recalled later. 
It allows the user to revert files back to a previous state or even revert the entire project back 
to a previous state, compare changes over time, see the modifications that were made, and the user 
responsible for those changes.

In this class assignment, Git was the version control system used, which consists of an open-source
distributed version control system. 


## Part 1: Working directly on the main branch

### Creating a new repository
For this part of the assignment, a new repository was created on GitHub.
To access this repository, click [here](https://github.com/maria-neto/devops-23-24-JPE-1231842.git).
To provide a better comprehension of all the steps involved in the development of
this assignment, a list of the commands used in the command line will be provided, as 
well as a brief explanation of each one.

### Initializing the repository

 1. Initialize the repository:
```bash 
git init
```
 2. Add a first version of the README.md file to the staging area:
```bash
git add README.md
```
 3. Commit the changes made to the README.md file:
```bash
git commit -m "first commit"
```
 4. Rename the master branch to main branch:
```bash
git branch -M main
```
 5. Add remote repository, previously created on GitHub, to the local repository:
```bash
git remote add origin https://github.com/maria-neto/devops-23-24-JPE-1231842.git
```
 6. Finally, push all the changes made to the remote repository:
```bash
git push -u origin main
```

### Adding a new file to the repository
Before adding a new file to the repository, a new folder was created using:
```bash
mkdir CA1
```
Assuming that the project *Tutorial React.js and Spring Data REST Application* is already
available on the local machine, the following commands were used to add the project to the repository:

 1. Add the basic folder of the project to the repository:
```bash
git mv path/to/TutorialReact.js/And/Spring/Data/REST/Application/basic CA1
```
 2. Add the changes made to the staging area:
```bash
git add CA1
```
 3. Commit the changes made to the repository:
```bash
git commit -m "added folder CA1 with Tutorial React.js and Spring Data REST Application"
```
 4. Push the changes made to the remote repository:
```bash
git push origin main
```

### Creating a tag for the first version of the project
 1. Create tag for the first version of the project, which includes a brief message:
```bash
git tag -a v.1.1.0 -m "initial version"
```
 2. Push the tag to the remote repository:
```bash
git push origin v.1.1.0
```

### Adding a new feature to the project - Job Years field
The original project was modified, specifically the Employee class, to include the number
of years the employee has been working in the company. Also, a test package was created to
add some unit tests to the project. Here is a brief example of the alternations
that were introduced in the Employee class:
```java
    private int jobYears;
    
    public Employee(String firstName, String lastName, String description, int jobYears) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.jobYears = jobYears;
    }
    
    public int getJobYears() {
        return jobYears;
    }

    public void setJobYears(int jobYears) {
        this.jobYears = jobYears;
    }
```
After these modifications were introduced, the following commands were used to add the changes to the repository:

 1. Add the changes made to the staging area
```bash
git add .
```
This command was used as it was intended to add all the modification added, and commit them.

 2. Commit the changes made to the repository:
```bash
git commit -m "job years added to Employee class, class EmployeeTest created"
```
 3. Push the changes made to the remote repository:
```bash
git push origin main
```
A new tag was created to mark the addition of a new feature:
```bash
git tag -a v1.2.0 -m "Job Years feature added and tested"
git push origin v1.2.0
```

### Adding validation to the attributes of the Employee class
The Employee class was modified to include validation of its attributes. A method named
areArgumentsValid was created in order to validate the first name, last name, description and
job years. Here is a representation of the method, as well as its usage in the constructor:
```java
    private boolean areArgumentsValid(String firstName, String lastName, String description, int jobYears, String email) {
        if( firstName == null || firstName.trim().isEmpty())
        return false;
        if( lastName == null || lastName.trim().isEmpty())
        return false;
        if( description == null || description.trim().isEmpty())
        return false;
        if( jobYears < 0)
        return false;
        return true;
        }

    public Employee(String firstName, String lastName, String description, int jobYears) throws InstantiationException {
        if( !areArgumentsValid(firstName, lastName, description, jobYears, email) || !isEmailAddressValid(email))
        throw new InstantiationException("Invalid arguments");
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.jobYears = jobYears;
        }
```
After all the alterations were implemented, the commands used previously were applied in order to commit
and push the changes to the remote repository. An issue was created to keep track of the changes made to the project, and
a reference to the issue (#2) was added to the commit message.
```bash
git add .
git commit -m "#2 created methods to validate arguments"
git push origin main
```
This new feature and validation of the attributes marked the end of the first part of the assignment, so
a new tag was created to mark the end of this part of the project.
```bash
git tag -a v1.3.0 -m "Part 1 of the Class Assignment 1 completed"
git push origin v1.3.0
```

## Part 2: Working on a new branch to add a new feature
For the second part of this assignment, it was intended to create a new branch in order to develop a new 
feature, and then merge it with the main branch once the feature was finished.
For this project, the addition of an e-mail field was required to the attributes of the employee. As such,
the new branch was named after the feature being developed, in this case, email-field.

### Creating a new branch
 1. To create the new branch (while in the main branch), the following command was applied:
```bash
git branch email-field
```
 2. Command to switch from the main branch to the email-field branch:
```bash
git checkout email-field
```
 3. Command to push the changes made to the remote repository:
```bash
git push -u origin email-field
```

### Adding a new feature to the project - Email field
As it was requested, a new feature was added to the project, concerning the e-mail address of the employee.
In this stage, simple validations were introduced (e.g. the e-mail address cannot be null or empty), and a
few new unit tests were added. Below is a representation of the changes made to the Employee class.
```java
    private String email;

    public Employee(String firstName, String lastName, String description, int jobYears, String email) throws InstantiationException {
        if(!areArgumentsValid(firstName, lastName, description, jobYears, email))
        throw new InstantiationException("Invalid arguments");
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.jobYears = jobYears;
        this.email = email;
        }

    private boolean areArgumentsValid(String firstName, String lastName, String description, int jobYears, String email) {
        if( firstName == null || firstName.trim().isEmpty())
        return false;
        if( lastName == null || lastName.trim().isEmpty())
        return false;
        if( description == null || description.trim().isEmpty())
        return false;
        if( jobYears < 0)
        return false;
        if (email == null || email.trim().isEmpty())
        return false;
        return true;
        }
    
    public String getEmail() {
        return email;
        }
    
    public void setEmail(String email) {
        this.email = email;
        }
```
After all the alteration were implemented, they were committed, the email-field branch was merged with
the main branch, and was pushed to the remote repository. These modification were related to some previously
created issues, so those were referenced in the commit message.
```bash
git add .
git commit -m "#3 email attribute created in Employee class, get and set methods added, tests updated in EmployeeTest"
git checkout main
git merge --no-ff email-field
git push origin main
```
The command ``git merge --no-ff email-field`` was used to merge the email-field branch with the main branch, and 
avoid creating a merge commit, thus using the no fast-forward option.
Before merging, a command to pull the changes made to the remote repository could be executed, to avoid merging
conflicts. However, as there is only one collaborator in this project, this step was not necessary.

As a new feature was successfully added, a new tag was created to mark the new version of this project.
```bash
git tag -a v1.3.0 -m "email field added to Employee"
git push origin v1.3.0
```

### Adding a new feature to verify and validate e-mail addresses
The project was further modified to include a more thorough validation of e-mail addresses.
A new method named isEmailAddressValid was created to verify if the e-mail address is valid and 
regarding its composition (verification of the presence of the '@' and '.' characters, in the correct order).
To do so, a new branch was created, named fix-invalid-email, and the new feature was developed in this branch.
```bash
git branch fix-invalid-email
git checkout fix-invalid-email
git push -u origin fix-invalid-email
```

Here is a representation of the feature implementation:
```java
    private boolean isEmailAddressValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        }

    public Employee(String firstName, String lastName, String description, int jobYears, String email) throws InstantiationException {
        if( !areArgumentsValid(firstName, lastName, description, jobYears, email) || !isEmailAddressValid(email))
        throw new InstantiationException("Invalid arguments");
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.jobYears = jobYears;
        this.email = email;
        }

    public void setEmail(String email) {
        if (!isEmailAddressValid(email))
        throw new IllegalArgumentException("Invalid email");
        else {
        this.email = email;
        }
    }
```
For the development of the isEmailAddressValid method, the regular expression was obtained from this [reference](https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method).
After completing the implementation of this new feature and adding some unit tests, the commands used previously in the
development of the email-field feature were applied to commit and push the changes to the remote repository.
A new tag was created to mark the most recent version of the project.
```bash
git add .
git commit -m "#5 added method to verify if e-mail address is valid to constructor and setEmail, tests added"
git checkout main
git merge --no-ff fix-invalid-email
git push origin main
git tag -a v1.3.1 -m "added method to validate e-mail address"
git push origin v1.3.1
```

As this stage marked the completion of the second part of this class assignment, a different tag was created.
```bash
git tag -a ca1-part2 -m "implemented e-mail field and verification"
git push origin ca1-part2
```

## Alternative Version Control System 

### Subversion
Subversion (SVN) is a centralized version control system frequently used to manage and track changes to files in software development
projects. SVN uses a central server to store all the files and enables team members to check out files from the server, make changes
and then commit them back to the server.

### Differences between Git and Subversion

 **1. Distributed vs Centralized:**

While Git is a distributed version control system, meaning that each user has a complete copy of the repository, Subversion is a
centralized version control system, which means that there is only one single central repository containing all the files.

 **2. Branching and Merging:**

Git allows for easy and straightforward branching and merging. In fact, branching is an important part of the workflow, and merging
is a common operation. Subversion also supports branching and merging, where branches consist essentially in copies of the entire repository, 
making the process more complex and less flexible.

 **3. Performance:**

Git is commonly considered faster, specially when considering operations such as branching and merging. Subversion, on the other hand,
while efficient, it may not be as fast as Git, specially when dealing with large repositories.

 **4. Offline Work:**

Git allows offline work as each user has a complete copy of the repository (local repository), and can commit changes locally before pushing
them to the central (remote) repository. On the other hand, Subversion requires a network connection to the central repository to commit changes.

 **5. Atomic Commits:**

Git commits are atomic, which means all changes in a commit must be applied or none of them are. However, with Subversion, commits are not atomic
by default, and it is possible to commit only a part of the changes in a working copy, which may lead to inconsistencies.


### Analysis an implementation using Subversion
In order to further understand the differences between Git and Subversion, an example of implementation using Subversion was analyzed.

#### Creating a new repository
1. Create the repository:
```bash 
svnadmin create /path/to/repository
```
2. Checkout remote repository to local working directory:
```bash
svn checkout file:///path/to/repository/trunk /path/to/working/directory
```
In this command, the term *trunk* refers to the main development branch of the repository, serving a similar purpose to the main or master branch in Git.

3. Add a first version of the README.md file to the staging area:
```bash
svn add README.md
```
4. Commit the changes made to the README.md file:
```bash
svn commit -m "README.md file added to the repository"
```

Since Subversion is a centralized version control system, the commit command will send the changes made to the central repository.
As such, push and pull commands are not necessary, as the working directory is always synchronized with the central repository.
However, ``svn checkout`` and ``svn update`` commands are used to update the working directory with the latest changes from the
central repository. ``svn checkout`` is used to initially create a working directory from the central repository when starting
to work on a project for the first time, whereas ``svn update`` is used to update the working directory with changes introduced 
since the last update.

#### Tagging a version of the project
First, the user should navigate to the root of the working directory, and then create a tag marking the current version of the project.
```bash
cd /path/to/working/directory
svn copy trunk tags/v1.1.0 -m "initial version"
```

#### Adding a new feature to the project using a new branch
To add a new feature to the project, a new branch was created, named *email-field*.
```bash
svn mkdir branches/email-field -m "new branch to add email field"
```

Then, the user should switch to the new branch to start working on the new feature.
```bash
cd /path/to/working/directory/branches/email-field
```

Commit the changes made to the new branch.
```bash
svn commit -m "new feature added to the project"
```

After finishing the development of the new feature, the user should switch back to the trunk.
```bash
svn switch ^/trunk
```

Then, the changes made to the new branch should be merged into the trunk.
```bash
svn merge ^/branches/email-field
```
After merging, some conflicts may arise, and the user should resolve them before committing the changes to the trunk.

Finally, the user should commit the changes made to the trunk.
```bash
svn commit -m "new feature merged into trunk"
```



