int main() 
{
   int a;
   a = 5;
   b = 3;
   while (a < 3) { 
      a = a + 2;
      b = a + 4;
      while (b < 3) {
        b = b + 1;
      }
   }
   
   return a;
}