# exercise1
Sample app for creation of CSV file of fund performance

JDK version: 1.8
Maven packaging: mvn package
Main Java Class: App.java

Application propertes: app.properties contains settings for
  1. output filename
  2. location of CSV file sources
  3. date formatting
  
Assumptions:
  1. files always contain headers
  2. files have bom
  3. fund and benchmark date formats are different
  4. file may include duplicate records
