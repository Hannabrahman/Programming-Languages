#include "SingularValueDecomposition.h"
#include <ctime>

int main (int argc, char **argv){
  double start_s=clock();
  if (argc < 3){
    std::cout<<"SVD needs three input arguments"<<std::endl;
    return 0;
   }
 SVD mySVD = SVD();
 double start_r = clock();
 mySVD.ReadFile(argv[1]);
 double stop_r = clock();

 double start_cal=clock();
 mySVD.Calc_svd();
 double stop_cal = clock();

 double start_w=clock();
 mySVD.WriteFile(argv[2]);
 double stop_w = clock();
 // mySVD.Print();
 double stop_s=clock();
 std::cout << "total time: " << (stop_s-start_s)/double(CLOCKS_PER_SEC)*1000 << std::endl;
 std::cout << "Reading time: " << (stop_r-start_r)/double(CLOCKS_PER_SEC)*1000 << std::endl;
 std::cout << "SVD calculation time: " << (stop_cal-start_cal)/double(CLOCKS_PER_SEC)*1000 << std::endl;
 std::cout << "Writing time: " << (stop_w-start_w)/double(CLOCKS_PER_SEC)*1000 << std::endl;

}
