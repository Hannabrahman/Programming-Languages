# cmps203_project

## Java Implementation SvdJava/:

1. Run main program which can read matrix A from file and genearte decomposed matrices U, S, V where A = U\*S\*V' and save to files: 

```
java -jar build/libs/svd-all-1.0.jar <INPUT FILE> <OUTPUT DIRECTORY>
```
- INPUT FILE: path to input matrix, example input as in data/matrix.input. Note the program can calculate the dimension of the input matrix itself.

- OUTPUT DIRECTORY: directory path of output files. The program yields three files, matrix\_javasvd\_s.output, matrix\_javasvd\_u.output, matrix\_javasvd\_v.output which saves S, U, V matrices of input matrix respectively. 

2. Re-build application
```
gradle build fatJar
```

3. Run unit test
```
gradle test
```
- Run unit tests of classes

- You can open test report under build/reports/tests/test/index.html in a brower

## Resources:

http://svn.lirec.eu/libs/magicsquares/

http://math.nist.gov/javanumerics/jama/

http://people.revoledu.com/kardi/tutorial/LinearAlgebra/SVD.html

https://jeremykun.com/2016/05/16/singular-value-decomposition-part-2-theorem-proof-algorithm/
