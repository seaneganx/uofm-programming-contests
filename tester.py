#!/usr/bin/env python
import argparse
from argparse import RawTextHelpFormatter
import os
import subprocess

parser = argparse.ArgumentParser(description='Test your solution against the contest judging data.', formatter_class=RawTextHelpFormatter)

parser.add_argument (
	'-c',
	metavar='contest',
	dest='contest',
	required=True,
	help='\n'.join([
		'Specifies which contest you are writing.',
		'This is the subfolder where the program will look for input/output files.' ])
)

parser.add_argument (
	'-p',
	metavar='problem',
	dest='problem',
	required=True,
	help='\n'.join([
		'Specifies which problem you are testing.',
		'This is the name the program will look for as input/output files.',
		'Do not include the file extension (.in or .out)' ])
)

args = parser.parse_args()

# produce the non-relative file paths needed
problem_path = "{}/{}/{}".format(os.path.abspath(os.path.dirname(__file__)), args.contest, args.problem)
in_path = problem_path + '.in'
out_path = problem_path + '.out'
solution_path = problem_path + '.py'

# open the user's solution using subprocess, replacing stdin with our judging input data, and replacing stdout with our subprocess pipe
input_file = open(in_path)
process = subprocess.Popen(['python', solution_path], stdout=subprocess.PIPE, stdin=input_file)

# get the user's solution output from subprocess
solution_output = process.communicate()[0].upper().splitlines()

# open the judging output data and read each line into a list, then close the file
with open(out_path) as file:
    judge_output = file.read().splitlines()
file.close()

# loop through the user and judge output data until we find something that doesn't match
equalCount = 0
equalStatus = len(solution_output) == len(judge_output)
while(equalStatus and equalCount < len(judge_output)):
	equalStatus = judge_output[equalCount] == solution_output[equalCount]
	equalCount += 1

if equalStatus:
	print "Pass."
else:
	print "Fail."