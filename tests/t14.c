int main() {
  while (a < b && c == d || a - 3 >= c) {
  	d = foo(bar(a, b));
  }
}

int foo(int a) {
  return 2*a;
}

int bar(int a, int b) {
  return a + b;
}