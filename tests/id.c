int main( int dd, int dr )
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


