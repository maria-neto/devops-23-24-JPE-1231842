# Report for Class Assignment 1 : Version Control with Git

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
 1. Command used to initialize the repository:
```bash 
git init
```
 2. To add a first version of the README.md file, the following command was used:
```bash
git add README.md
```
 3. Command required to commit the changes made to the README.md file:
```bash
git commit -m "first commit"
```
 4. Command used to rename the master branch to main:
```bash
git branch -M main
```
 5. To add the remote repository previously created on GitHub, the following command was used:
```bash
git remote add origin https://github.com/maria-neto/devops-23-24-JPE-1231842.git
```
 6. Finally, to push all the changes made to the remote repository, the following command was used:
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
 1. Command used to add the basic folder of the project to the repository:
```bash
git mv path/to/TutorialReact.js/And/Spring/Data/REST/Application/basic CA1
```
 2. Command used to add the changes made to the repository:
```bash
git add CA1
```
 3. Command used to commit the changes made to the repository:
```bash
git commit -m "added folder CA1 with Tutorial React.js and Spring Data REST Application"
```
 4. Command used to push the changes made to the remote repository:
```bash
git push origin main
```

### Creating a tag for the first version of the project
 1. Command used to tag the first version of the project, which includes a brief message:
```bash
git tag -a v.1.1.0 -m "initial version"
```
 2. Command used to push the tag to the remote repository:
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
 1. Command used to add the changes made to the repository:
```bash
git add .
```
This command was used as it was intended to add all the modification added, and commit them.
 2. Command used to commit the changes made to the repository:
```bash
git commit -m "job years added to Employee class, class EmployeeTest created"
```
 3. Command used to push the changes made to the remote repository:
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

## Part 2: Working on a feature branch











```



