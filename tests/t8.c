int main() 
{
   int a, b;
   a = 5;
   b = 3;
   if (a < b) {
     while (a < 3) { 
      a = a + 2;
      b = a + 4;
      while (b < 3) {
        b = b + 1;
      }
     }
   } else {
     a -= 3;
   }
   
   return a;
}