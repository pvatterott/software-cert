int main() 
{
   int a, b, c;
   a = 5;
   b = 3;
   while (a < 3) { 
      c = foo(a, b);
      a = a + 1;
   }
   
   return c;
}

int foo(int a, int b)
{
   return a + b;
}