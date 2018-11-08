#include <stdio.h>
#include <stdlib.h>


void main(int argc, char*argv[]) {

	FILE *fin = fopen("file.txt", "r");
	FILE *fout = fopen("out.txt", "w");

	int i;
	char c;
	while ( (c = getc(fin)) != EOF ) {
		putc((char)( ((int) c) ^ 10), fout);
	}
	
	fclose(fin);
	fclose(fout);
}
