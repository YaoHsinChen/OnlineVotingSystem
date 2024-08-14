1. This online voting system is just a simple demo which includes UI, API and store procedures.
2. The System can only run on the local. when testing on local, all dependency/environment should be set before running.
  - vue
  - MYSQL
  - SpringBoot
  - Maven
3. store procedure should be deployed with following sequence:
  - create_tables
  - create_procedures
  - insert_data
4. after DB setting is finished, run voting-system which is an API server
5. run VUE UI
