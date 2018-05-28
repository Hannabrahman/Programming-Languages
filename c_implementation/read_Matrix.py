# f = open ( 'input.txt' , 'r')
import sys
f = sys.stdin
l = [[int(num) for num in line.split(',')] for line in f ]
print(l)

for i in range(len(l)):
    for j in range(len(l[0])):
        print("m["+str(i)+"]["+str(j)+"]\n")
