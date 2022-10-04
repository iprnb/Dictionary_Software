package Dictionary;

public class Word {
    String word;
    String[] def = new String[10];

    public void setWord(String w) {
        word = w;
    }

    public void setDef(String[] d) {
        for (int i = 0; i < d.length; i++) {
            def[i] = d[i];
        }
    }

    public String getWord() {
        return word;
    }


    public Word[] findWord( Word[] word) {
        boolean f = false;
        String w;
        System.out.print("Enter the word: ");
        w = Driver.input();
        for (int i = 0; i < word.length; i++) {
            if (word[i] != null) {
                if (w.equalsIgnoreCase(word[i].word)) {
                    System.out.print(w + " : " + word[i].def[0]);
                    for (int j = 1; j < word[i].def.length; j++) {
                        if (word[i].def[j] != null)
                            System.out.print(" , " + word[i].def[j]);
                        else
                            break;
                    }
                    System.out.println("\n");
                    f = true;
                    break;
                }
            } else
                break;
        }
        if (f == false) {
            boolean includes = false;
            String sub;
            int counter = 0;
            int[] sim = new int[100];
            System.out.println("There was no result for '" + w + "'\n");
            for (int i = (w.length() / 2)+1; i < w.length(); i++) {
                for (int j = 0; j < w.length() - i+1; j++) {
                    sub = w.substring(j, i+j).toLowerCase();
                    for (int x = 0; x < word.length; x++) {
                        if (word[x] == null)
                            break;
                        if (word[x].word.contains(sub)) {
                            for (int z = 0; z < counter; z++) {
                                if (word[x].word.equals(word[sim[z]].word)) {
                                    includes = true;
                                    break;
                                }
                            }
                            if (includes == false) {
                                sim[counter] = x;
                                counter++;
                            }
                        }
                    }

                }
            }
            for (int x = 0; x < counter; x++) {
                System.out.print(word[sim[x]].word + " : " + word[sim[x]].def[0]);
                for (int t = 1; t < word[sim[x]].def.length; t++) {
                    if (word[sim[x]].def[t] == null)
                        break;
                    System.out.print(" , " + word[sim[x]].def[t]);
                }
                System.out.println("\n");
            }
        }
        return word;
    }

    public Word[] addWord(Word[] word) {
        String w,num;
        int n,charnum, counter=0;
        System.out.print("Enter the new word:");
        w = Driver.input().toLowerCase();
        for (int i = 0; i < 100; i++) {
            if (word[i] == null)
                break;
            counter++;
            if (w.equals(word[i].word)) {
                System.out.println("The word already exist!");
                return word;
            }
        }
        System.out.print("Enter the number of defenitions: ");
        num = Driver.input();
        charnum = (int)num.charAt(0);
        if((charnum<='0'||charnum>'9')){
            System.out.println("invalid input!");
        } else{
            if(num.length()==2){
                charnum=(int)num.charAt(1);
                if((charnum<'0'||charnum>'9')){
                    System.out.println("invalid input!");
                }
            }else{
                n=Integer.parseInt(num);
                boolean equal = false;
                String[] defs = new String[n];
                for (int i = 1; i <= n; i++) {
                    equal=false;
                    System.out.print("Enter the defenition #" + i+": ");
                    String s =Driver.input().toLowerCase();;
                    for(int j=0; j<i-1; j++){
                        if(defs[j].equals(s)){
                            System.out.println("The defenition already exists!");
                            i--;
                            equal = true;
                            break;
                        }
                    }
                    if(equal==false){
                        defs[i - 1] = s;
                    }
                }
                for(int i=0; i<w.length(); i++){
                    int ch = (int)w.charAt(i);
                    if(!((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')||ch==' '||ch=='-')){
                        System.out.println("invalid Word");
                        return word;
                    }
                }
                word[counter] = new Word();
                word[counter].word=w;
                for(int i=0; i<n; i++)
                    word[counter].def[i]=defs[i];
                for(int i=0; i<=counter; i++){
                    if(word[counter].word.compareTo(word[i].word)<0){
                        Word temp = word[i];
                        word[i] = word[counter];
                        for(int j = counter; j>i+1; j--){
                            word[j] = word[j-1];
                        }
                        word[i+1] = temp;
                    }
                }
                System.out.println("A new word was added successfully!");
            }
        }
        return word;

    }

    public Word[] updateWord(Word[] word){
        String w,in;
        int line ,c=-1;
        System.out.print("Enter the word you want to edit: ");
        w = Driver.input().toLowerCase();
        for (int i = 0; i < 100; i++) {
            if (word[i] == null)
                break;
            if (w.equals(word[i].word)) {
                c=i;
            }
        }
        if(c==-1)
            System.out.println("The word doesn't exist");
        else{
                int i=0;
                while(word[c].def[i]!=null){
                    i++;
                }
                System.out.println("Enter the number of defenition you want to edit(enter "+i+" for adding a new defenition): ");
                for(int i1=0; i1<i; i1++){
                    System.out.println("Defenition #"+i1+": "+word[c].def[i1]);
                }
                line = Integer.parseInt(Driver.input());
                if(line<i && line>=0){
                    System.out.print("Enter the new defenition: ");
                    in = Driver.input().toLowerCase();
                    for(int d=0; d<i; d++){
                        if(word[c].def[d].equals(in)){
                            System.out.println("The defenition already exists!");
                            return word;
                        }
                    }
                    word[c].def[line] = in;
                }
                else if(line==i){
                    System.out.print("Enter the new defenition: ");
                    in = Driver.input().toLowerCase();
                    for(int d=0; d<i; d++){
                        if(word[c].def[d].equals(in)){
                            System.out.println("The defenition already exists!");
                            return word;
                        }
                    }
                    int i1=0;
                    while(word[c].def[i1]!=null)
                        i1++;
                    word[c].def[i1] = in;
                }else{
                    System.out.println("Invalid input!");
                    return word;
                }
            System.out.println("The word was updated successfully!");
        }
        return word;
    }


}
