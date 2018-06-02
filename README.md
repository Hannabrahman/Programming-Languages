# cmps203_project

## Java Implementation SvdJava/:

1. Run main program which can read matrix A from file and genearte decomposed matrices U, S, V where A = U\*S\*V' and save to files: 

```
java -jar build/libs/svd-all-1.0.jar <INPUT FILE> <OUTPUT DIRECTORY>
```
- INPUT FILE: path to input matrix, example input as in data/matrix.input. Note the program can calculate the dimension of the input matrix itself.

- OUTPUT DIRECTORY: directory path of output files. The program yields three files, matrix\_javasvd\_s.output, matrix\_javasvd\_u.output, matrix\_javasvd\_v.output which save decomposed S, U, V matrices of input matrix respectively. 

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

## C++ implementation in SVDcpp/

1. Compile SDV.cpp program and then RUN the executable file which will read an input Matrix A from INPUT FILE and generate decompose matrices V, U and S and save it in OUTPUT FILE. ( A = U * S * V')

```
g++ runSVD.cpp SingularValueDecomposition.cpp -o <executable_file_name>
./<executable_file_name> <INPUT FILE> <OUTPUT DIRECTORY>
```
- INPUT FILE: path to input matrix, example input as in data/matrix_cpp.input. Note that Matrix dimensions needs to be specified in the first line as in example.

- OUTPUT DIRECTORY: directory path of output files. The program yields two files, matrix\_javacpp\_S.output, matrix\_cppsvd\_V.output, matrix\_javasvd\_v.output which saves Singular Values (S) and right singular vector (V), respectively.


## Resources:

http://svn.lirec.eu/libs/magicsquares/

http://math.nist.gov/javanumerics/jama/

http://people.revoledu.com/kardi/tutorial/LinearAlgebra/SVD.html

https://jeremykun.com/2016/05/16/singular-value-decomposition-part-2-theorem-proof-algorithm/
