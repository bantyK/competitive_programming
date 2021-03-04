// 1634 https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/
class PolyNode {
    int coefficient, power;
    PolyNode next = null;

    PolyNode() {
    }

    PolyNode(int x, int y) {
        this.coefficient = x;
        this.power = y;
    }

    PolyNode(int x, int y, PolyNode next) {
        this.coefficient = x;
        this.power = y;
        this.next = next;
    }
}

public class AddPolynomials {

    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode(-1, -1);
        PolyNode current = dummy;

        while (poly1 != null || poly2 != null) {
            if (poly1.power > poly2.power) {
                current.next = new PolyNode(poly1.coefficient, poly1.power);
                poly1 = poly1.next;
                current = current.next;
            } else if (poly2.power > poly1.power) {
                current.next = new PolyNode(poly2.coefficient, poly2.power);
                poly2 = poly2.next;
                current = current.next;
            } else {
                if (poly1.coefficient + poly2.coefficient != 0) {
                    current.next = new PolyNode(poly1.coefficient + poly2.coefficient, poly2.power);
                    current = current.next;
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
        }

        while (poly1 != null) {
            current.next = new PolyNode(poly1.coefficient, poly1.power);
            poly1 = poly1.next;
            current = current.next;
        }

        while (poly2 != null) {
            current.next = new PolyNode(poly2.coefficient, poly2.power);
            poly2 = poly2.next;
            current = current.next;
        }
        return dummy.next;
    }


}