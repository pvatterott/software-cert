int main() {
  int a, b, c;
  a = 3;
  b = 4;
  
  c = foo(a, b) + 3;
  return b * bar(c, a); 
}

int foo(int a, int b) {
  if (a < b) {
    return a;
  } else {
    return b;
  }
}

int bar(int a, int b) {
  int c;
  c = 0;
  while (a < b) {
    a = a + 1;
    c = c + a;
  }
  return c;
}