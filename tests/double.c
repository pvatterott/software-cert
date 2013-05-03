int main() {
  int a = 3;
  double b = 2.5;
  int c;
  
  c = foo(a) + (int)b;
  return c;
}

int foo(int a) {
  return 3 * a;
}