#include "SingularValueDecomposition.h"
#include <ctime>

int main (int argc, char **argv){
  int start_s=clock();
  if (argc < 3){
    std::cout<<"SVD needs three input arguments"<<std::endl;
    return 0;
   }
 SVD mySVD = SVD();
 mySVD.ReadFile(argv[1]);
 mySVD.Calc_svd();
 mySVD.WriteFile(argv[2]);
 // mySVD.Print();
 int stop_s=clock();
 std::cout << "time: " << (stop_s-start_s)/double(CLOCKS_PER_SEC)*1000 << std::endl;

}
