#!/usr/bin/env python3
from sys import argv
import webbrowser
problemStatement = argv[1:]
url = "http://www.leetcode.com/problems/" + "-".join(problemStatement).lower()
webbrowser.open_new_tab(url)