nmon (Nigel's Monitor) is a computer performance system monitor tool for the AIX and Linux operating systems. The nmon tool has two modes 1) displays the performance stats on-screen in a condensed format or 2) the same stats are saved to a data file for later analysis.

Below link provides further details on the nmon command:
https://www.ibm.com/support/knowledgecenter/ssw_aix_71/n_commands/nmon.html

The program reads an NMON log file as input file and parses this file to fetch the statistics related to CPU usage, Memory Usage, Network Usage and Disk Usage of the server.
A sample NMON log file has been added in the package.

**How to execute:**

Import the project and run the main programme to execute. Once the program is executed, it reads the input NMON log file, parses the required data and displays the final report in an output file with the name "nmon_output.txt". 
