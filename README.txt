Short Project 11: Divide and conquer, Enumeration

Leejia James and Vishwanath D C

Team task:
1. Implemented the expected O(n) algorithm for the k largest elements (select)
   of an array, and compared its performance with the algorithm using
   priority queues that is designed for the same problem on streams.
   Used k=n/2 (median), and tried large values of n: 16M, 32M, 64M, 128M, 256M.

How to Compile:
1. Unzip contents of lxj171130.zip into a folder named "lxj171130".
2. Run the command `javac lxj171130/*.java` to compile.
3. Run the command `java lxj171130.Driver <n> <choice>` to execute the program.

where,
n - Number of elements in the array
choice - 1 or 2
	1 for O(n) algorithm
	2 for the algorithm using priority queues