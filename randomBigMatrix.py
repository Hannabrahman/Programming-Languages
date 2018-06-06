import sys
import numpy as np
import argparse

parser = argparse.ArgumentParser(description='')
parser.add_argument('--row', '-row',type=int)
parser.add_argument('--col', '-col',type=int)

args = parser.parse_args()

l = np.random.uniform(-30,30,(args.row,args.col))

sys.stdout.write(str(args.row)+' '+str(args.col)+'\n')
for i in range(l.shape[0]):
    txt = ''
    for j in range(l.shape[1]):
        if j != l.shape[1] - 1:
            txt += str(l[i][j])+','
        else:
            txt += str(l[i][j])+';\n'
    sys.stdout.write(txt)
