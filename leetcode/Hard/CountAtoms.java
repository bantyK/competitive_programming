import java.util.*;


//726 https://leetcode.com/problems/number-of-atoms/
public class CountAtoms {

    public static void main(String[] args) {
        CountAtoms obj = new CountAtoms();
        System.out.println(obj.countOfAtoms("Mg(OH)2"));

        System.out.println(obj.countOfAtoms("H2O").equals("H2O"));
        System.out.println(obj.countOfAtoms("K4(ON(SO3)2)2").equals("K4N2O14S4"));
        System.out.println(obj.countOfAtoms("Mg(OH)2K213(ON)4").equals("H2K213MgN4O6"));
    }

    public String countOfAtoms(String formula) {
        Stack<Molecule> stack = new Stack<>();
        int idx = 0;
        while (idx < formula.length()) {
            char ch = formula.charAt(idx);

            if (ch == '(') {
                stack.push(new Molecule("(", 0));
                idx++;
            } else if (ch == ')') {
                String count = getCount(formula, idx + 1);
                int multiplier = count.equals("") ? 1 : Integer.parseInt(count);

                List<Molecule> list = new ArrayList<>();
                while (!stack.isEmpty() && !stack.peek().isDelimiter()) {
                    Molecule top = stack.pop();
                    top.atomCount *= multiplier;
                    list.add(top);
                }
                stack.pop(); // pop the delimiter
                for (Molecule molecule : list) {
                    stack.push(molecule);
                }
                // advance the index to point to the char one after the count of this closing bracket
                idx += count.length() + 1;
            } else if (Character.isUpperCase(ch)) {
                // element name starts here
                String name = getName(formula, idx);
                // get the count of atoms of this elements
                idx += name.length();
                String count = getCount(formula, idx);
                if (count.equals("")) {
                    stack.push(new Molecule(name, 1));
                } else {
                    stack.push(new Molecule(name, Integer.parseInt(count)));
                }
                idx += count.length();
            }
        }

        TreeMap<String, Integer> map = new TreeMap<>();
        while (!stack.isEmpty()) {
            Molecule molecule = stack.pop();
            if (map.containsKey(molecule.name)) {
                map.put(molecule.name, map.get(molecule.name) + molecule.atomCount);
            } else {
                map.put(molecule.name, molecule.atomCount);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String key : map.keySet()) {
            builder.append(key);
            if (map.get(key) > 1)
                builder.append(map.get(key));
        }

        return builder.toString();
    }

    private String getCount(String formula, int start) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            // this loop will exit when the current char is either a capital letter,
            // or a closing bracket
            if (Character.isUpperCase(ch) || ch == ')' || ch == '(') {
                break;
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    private String getName(String formula, int start) {
        StringBuilder builder = new StringBuilder();
        builder.append(formula.charAt(start));
        for (int i = start + 1; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            // this loop will exit when the current char is either a capital letter,
            // or a closing bracket
            if (Character.isDigit(ch) || Character.isUpperCase(ch) || ch == '(' || ch == ')') {
                break;
            }
            builder.append(ch);
        }
        return builder.toString();
    }


    private static class Molecule {
        String name;
        int atomCount;


        public Molecule(String name, int atomCount) {
            this.name = name;
            this.atomCount = atomCount;
        }

        public boolean isDelimiter() {
            return name.equals("(");
        }
    }
}