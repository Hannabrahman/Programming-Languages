/*
 * Modified by: Faeze Brahman
 * Date: May 28, 2018

 */

 #include <assert.h>
 #include <iostream>
 #include <stdio.h>
 #include <stdlib.h>
 #include <math.h>
 #include <algorithm>
 #include <list>
 #include "Matrix.h"
 #include <fstream>      // std::ifstream
 #include <string>


 #ifndef SVD_H
 #define SVD_H

 class SVD{
public:
   SVD ();
   SVD (Matrix<float> a);
   //SVD (std::string in_file);
   int dsvd(Matrix<float> &a, int m, int n, float *w, Matrix<float> &v);
   void Calc_svd ();
   void ReadFile (const char * in_file);
   void WriteFile (const char * out_file);
   void Print() const;

 private:
   static double PYTHAG(double a, double b);
   int m;
   int n;
   Matrix<float> a;
   Vector<float> w;
   Matrix<float> v;
 };

 #endif
