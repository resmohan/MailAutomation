Assignment 3 - Mail Generation Application

This application can be invoked by running the main method in MailGenerationApp.
It is expecting certain parameters to be passed. Sample usage is given below.

Format:

--email  only generate email messages
--email-template <file>  accept a filename that holds the email template.
Required if --email is used

--letter only generate letters
--letter-template <file> accept a filename that holds the letter template.
Required if --letter is used

--output-dir <path> accept the name of a folder, all output is placed in this folder
--csv-file <path> accept the name of the csv file to process

Usage:

--output-dir and --csv-file is always required.
Along with this, either --email or --letter should be given.

If --email is given, then --email-template must also be provided;
if --letter is given then --letter-template must also be given.

It supports both absolute path and relative path.
For junits to complete successfully, following folders and files should be created in the current working directory :
1. CustomerData/insurance-company-members.csv
2. Letters/LetterTemplate1.txt
3. Emails/EmailTemplate1.txt
4. Output

Examples:
--csv-file CustomerData/insurance-company-members.csv --letter-template Letters/LetterTemplate1.txt --output-dir Output --letter

--csv-file CustomerData/insurance-company-members.csv --email-template Emails/EmailTemplate1.txt --output-dir Output --email


