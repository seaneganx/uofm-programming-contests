#!/usr/bin/env bash
for i in {08..15}; do
	contest="manitoba20$i"
	mkdir $contest
	wget -r -nH -nd -np -A '*.in','*.out' -P "./$contest/" "http://cs.umanitoba.ca/~acmpc/past/io_$i/"
	rm "./$contest/robots.txt"
done