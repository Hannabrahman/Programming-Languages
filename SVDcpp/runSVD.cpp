#include "SingularValueDecomposition.h"
#include <ctime>

int main (int argc, char **argv){
  if (argc < 3){
    std::cout<<"SVD needs three input arguments"<<std::endl;
    return 0;
   }
 SVD mySVD = SVD();
 mySVD.ReadFile(argv[1]);
 mySVD.Calc_svd();
 mySVD.WriteFile(argv[2]);
 // mySVD.Print();

}
