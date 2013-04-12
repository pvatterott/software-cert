int IntegerDivision ( int dd, int dr )
{
   int q, r;
   q = 0;
   r = dd;
   while (r >= dr) {
	q = q + 1;
 	r = r - dr;
   }
   return r; 
}

int main ( int X, int Y )
{
 int rem;
 rem = 0;
 while (Y > 0) {
   rem = IntegerDivision (X, Y);
   X = Y;
   Y = rem;
 }
 return X;
}