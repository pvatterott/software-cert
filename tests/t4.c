int main() {
	int a, b, c, d;
	a = 5;
	b = 6;
	a = a + 1;
	
	if (a < b) {
		c = a - b;
		d = 5;
	} else {
		c = a + b;
		d = 6;
	}

	d = a + c;
	return a;
}

