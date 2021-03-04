// SRTF kokugosogo
//=====================================================================================
// メモ
// ジョブの到着時点での最も処理時間の短いジョブから優先してジョブを処理
//
// コンパイルコマンド : javac -encoding UTF-8 SRTF.java
// 実行コマンド : java SRTF
// 入力データ例-----
// 4
// 0 7
// 2 6
// 5 1
// 7 2
// ----------------
//=====================================================================================

import java.util.Scanner;

public class SRTF {
    public static int dbg = 0; // DEBUGするかどうか→0ならする
    public static int bigv = 0x10000000; // BIG VALUE 最大のジョブ到着時刻、処理時間
    public static int ARV = 0; // ARRIVE ジョブの到着時刻
    public static int TIM = 1; // TIME ジョブの処理時間
    public static int REM = 2; // REMAIN ジョブの残り処理時間
    public static int STA = 3; // START ジョブの処理開始時刻
    public static int FIN = 4; // FINISH ジョブが処理を終了した時刻
    public static int TA = 5; // TURN ARROUND TIME ジョブを処理し終えたところから最短処理時間までの差
    public static int J[][] = new int[110][6]; // ジョブを記憶しておく配列
    public static int j[] = new int[6]; // 今調べているジョブを記憶しておく配列

    public static void comp(int n) {
        int t = 0; // 時刻
        int sum = 0; // ジョブの処理時間の合計
        while (true) {
            int wmin = bigv; // ジョブの最低処理時間
            int wmax = -1; // 1番処理時間が残っているジョブの処理時間を記憶
            int idx = -1;
            for (int i = 0; i < n; i++) {
                j = J[i];
                if (t >= j[ARV] && j[REM] > 0 && wmin > j[REM]) { // ジョブが到着していて、まだ処理し終えていなくて、処理時間が一番短いジョブを選ぶ
                    wmin = j[REM];
                    idx = i;
                }
                if (wmax < j[REM]) { // 1番処理時間の残っているジョブの処理時間をwmaxに保存
                    wmax = j[REM];
                }
            }
            if (wmax == 0) { // ジョブの処理時間に残りがなければbreak
                break;
            }
            if (idx >= 0) {
                j = J[idx];
                if (dbg == 0) { // デバッグのための出力
                    System.out.println("t=" + t + ", idx=" + idx + ", remain=" + j[REM]);
                }
                if (j[STA] >= t) {
                    j[STA] = t; // ジョブが処理を開始された時刻を記憶
                }
                j[REM] -= 1; // ジョブを1だけ進めた
            }
            t = t + 1; // 時刻の経過
            if (idx >= 0) {
                j = J[idx];
                if (j[REM] == 0) {
                    j[FIN] = t; // ジョブが終了した時刻を記憶
                    j[TA] = t - j[ARV]; // ジョブが到着してから何時刻後に処理が終わったか
                }
            }
        }
        for (int i = 0; i < n; i++) {
            j = J[i];
            System.out.println("job no=" + i + ", ARV=" + j[ARV] + ", FIN=" + j[FIN] + ", TA=" + j[TA]);
            sum += j[TA];
        }
        System.out.println("sum TA=" + sum + ", average TA=" + (sum / ((double) n)));
    }

    public static void main(String[] args) {
        int a;
        int b;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 110; i++) {
            for (int k = 0; k < 6; k++) {
                J[i][k] = bigv; // 初期値として最大のジョブ到着時刻、処理時間を代入
            }
        }
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            J[i][ARV] = a; // 到着時刻の代入
            J[i][TIM] = b; // ジョブの処理時間の代入
            J[i][REM] = b; // ジョブの処理時間を残り時間として代入
        }
        comp(n);
    }
}
