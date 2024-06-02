/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;



import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;

	private int jobYears;

	private String email;

	private Employee() {}

	public Employee(String firstName, String lastName, String description, int jobYears, String email) throws InstantiationException {
		if( !areArgumentsValid(firstName, lastName, description, jobYears, email) || !isEmailAddressValid(email))
			throw new InstantiationException("Invalid arguments");
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobYears = jobYears;
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
				(jobYears == employee.jobYears);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description);
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

	/**
	 *Reference:
	 * https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
	 */
	public boolean isEmailAddressValid(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getJobYears() {
		return jobYears;
	}

	public void setJobYears(int jobYears) {
		this.jobYears = jobYears;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!isEmailAddressValid(email))
			throw new IllegalArgumentException("Invalid email");
		else {
			this.email = email;
		}
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", jobYears='" + jobYears + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
// end::code[]
