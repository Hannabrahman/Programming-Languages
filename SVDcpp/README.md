input is a file:
fisrt line: m (# rows) n (#col)
matrix (space seperated)
example:
3 2
1 2
2 4
-1 3

## C++ implementation in SVDcpp/

1. Compile SDV.cpp program and then RUN the executable file which will read an input Matrix A from INPUT FILE and generate decompose matrices V, U and S and save it i
n OUTPUT FILE. ( A = U * S * V')

```
g++ runSVD.cpp SingularValueDecomposition.cpp -o <executable_file_name>
./<executable_file_name> <INPUT FILE> <OUTPUT DIRECTORY>
```
- INPUT FILE: path to input matrix, example input as in data/matrix_cpp.input. Note that Matrix dimensions needs to be specified in the first line as in example.

- OUTPUT DIRECTORY: directory path of output files. The program yields two files, matrix\_javacpp\_S.output, matrix\_cppsvd\_V.output, matrix\_javasvd\_v.output which
 saves Singular Values (S) and right singular vector (V), respectively.
