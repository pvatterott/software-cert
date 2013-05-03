
  int main() {
    int a = 1;
    int b = 2;
    a = b < 3;
    while (a < b) {
      a = foo(a+1);
    }
  }

  int foo(int a) {
    return 2 * a;
  }