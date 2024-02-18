        #include <iostream>
        #include <cstdlib>

        int main(int argc, char **argv)
        {

        	int sum = 0;
        	for (int i = 1; i < argc; ++i)
        	{
        		int num_i = atoi(argv[i]);
        		sum += num_i;
        	}
        	std::cout << sum;

        	return 0;
        }