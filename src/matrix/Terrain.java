package matrix;

public class Terrain {

	public static Ressource[][] creationTerrain() {
		
		Ressource[][] matrix6 = new Ressource[10][10];
		
        matrix6 [0][0] = new Ble("blé 01");
        matrix6 [1][1] = new Ble("blé 12");
        matrix6 [0][2] = new Ble("blé 12");
        matrix6 [1][3] = new Ble("blé 12");
        matrix6 [0][4] = new Ble("blé 12");
        matrix6 [1][5] = new Ble("blé 12");
        matrix6 [0][6] = new Ble("blé 12");
        matrix6 [1][7] = new Ble("blé 12");
        matrix6 [0][8] = new Ble("blé 12");
        matrix6 [1][9] = new Ble("blé 12");    
        matrix6 [5][5] = new Pierre("Jacques");
        matrix6 [3][1] = new Bois("bois31");
        matrix6 [4][8] = new Bois("bois48");
        matrix6 [8][2] = new Bois("bois82");
        matrix6 [8][3] = new Bois("bois83");
        matrix6 [8][9] = new Bois("bois89");
        
		return matrix6;
	}
}
