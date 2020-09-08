package omok;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class omoc {

	private int[][] map = new int[15][15];
	private int[] wxy = {7,7};
	private int[] bxy = {7,7};
	private Key key = new Key(wxy, bxy);
	private boolean turn = true; // true 이면 백돌 false 면 흑돌 차례
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
	    f.setSize(300,200);     
	    f.setLayout( null );
	    f.setVisible(true);
	    
		omoc o = new omoc();
		o.start();
		f.addKeyListener(o.key);
	}
	
	private void start() {
		printmap();
		
	}

	
	private void printmap() {
		System.out.println("\n\n\n\n\n");
		System.out.println("┌─────────────────────────────────────────────┐");
		for(int i=0; i<map.length;i++){
			for(int j=0; j<map[i].length;j++){
				if(j==0){
					System.out.print("│");
				}
				if(map[i][j] == 1){
					if(i==wxy[1] && j==wxy[0] || i==bxy[1] && j==bxy[0]){
							System.out.print(">○<");
					} else {
						System.out.print(" ○ ");
					}
				} else if(map[i][j] == 2){
					if(i==wxy[1] && j==wxy[0] || i==bxy[1] && j==bxy[0]){
							System.out.print(">●<");
					} else {
						System.out.print(" ● ");
					}
				} else if (map[i][j] == 0) {
					if(turn){
						if(i==wxy[1] && j==wxy[0]){
							System.out.print("> <");
						} else {
							System.out.print("   ");
						}
					} else {
						if (i==bxy[1] && j==bxy[0]){
							System.out.print("> <");
						} else {
							System.out.print("   ");
						}
					}
				}
				if(j==14){
					System.out.print("│");
				}
			}
			System.out.println();
		}
		System.out.println("└─────────────────────────────────────────────┘");
	}
	
	
	class Key implements KeyListener{
		omoc o;
		private int[] wxy;
		private int[] bxy;
		
		public Key(int[] wxy, int[] bxy) {
			this.wxy = wxy;
			this.bxy = bxy;
		}
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 37){ // 좌
				if(turn){
					if(wxy[0] > 0){
						wxy[0]--;
						printmap();
					}
				} else {
					if(bxy[0] > 0){
						bxy[0]--;
						printmap();
					}
				}
			} else if(e.getKeyCode() == 39){ // 우
				if(turn){
					if(wxy[0] < 14){
						wxy[0]++;
						printmap();
					}
				} else {
					if(bxy[0] < 14){
						bxy[0]++;
						printmap();
					}
				}
			} else if(e.getKeyCode() == 40){ // 하
				if(turn){
					if(wxy[1] < 14){
						wxy[1]++;
						printmap();
					}
				}else {
					if(bxy[1] < 14){
						bxy[1]++;
						printmap();
					}
				}
			} else if(e.getKeyCode() == 38){ // 상
				if(turn){
					if(wxy[1] > 0){
						wxy[1]--;
						printmap();
					}
				}else {
					if(bxy[1] > 0){
						bxy[1]--;
						printmap();
					}
				}
			} else if(e.getKeyCode() == 10){ // 엔터
				if(turn){
					if(map[wxy[1]][wxy[0]] == 0){
						map[wxy[1]][wxy[0]] = 1;
						printmap();
						turn = false;
						whiteCheckWin();
					}
				}else {
					if(map[bxy[1]][bxy[0]] == 0){
						map[bxy[1]][bxy[0]] = 2;
						printmap();
						turn = true;
						blackCheckWin();
					}
				}
			}
			
		}
		
		
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private void blackCheckWin() {
		// 좌우 확인
				int cnt = 0;
				int cx = bxy[0];
				int cy = bxy[1];
				while(true){ // 좌측 확인
					cx--;
					if(cx >= 0){
						if(map[bxy[1]][cx] == 2){
							cnt++;
						} else {
							break;
						}
					} else {
						break;
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}
				}
				cx = bxy[0];
				cy = bxy[1];
				while(true){ // 우측 확인
					cx++;
					if(cx <= 14){
						if(map[bxy[1]][cx] == 2){
							cnt++;
						} else {
							break;
						}
					} else {
						break;
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}
				}
				if(cnt==4){
					System.out.println("흑돌이 이겼습니다.!");
					return;
				} // 좌우 확인 끝
				// 상하 확인
				cnt = 0;
				cx = bxy[0];
				cy = bxy[1];
				while(true){ // 위쪽 확인
					cy--;
					if(cy >= 0){
						if(map[cy][bxy[0]] == 2){
							cnt++;
						} else {
							break;
						}
					} else {
						break;
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}
				}
				cx = bxy[0];
				cy = bxy[1];
				while(true){ // 아래쪽 확인
					cy++;
					if(cy <= 14){
						if(map[cy][bxy[0]] == 2){
							cnt++;
						} else {
							break;
						}
					} else {
						break;
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}
				}
				if(cnt==4){
					System.out.println("흑돌이 이겼습니다.!");
					return;
				} // 상하 확인 끝
				//  / 대각선 확인
				cnt = 0;
				cx = bxy[0];
				cy = bxy[1];
				while(true){ // 우측위 확인
					cy--;
					cx++;
					if(cy >= 0 && cx <= 14){
						if(map[cy][cx] == 2){
							cnt++;
						} else {
							break;
						}
					} else {
						break;
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}
				}
				if(cnt==4){
					System.out.println("흑돌이 이겼습니다.!");
					return;
				} // 우측위 확인 끝
				cx = bxy[0];
				cy = bxy[1];
				while(true){ // 좌측아래 확인
					cy++;
					cx--;
					if(cy <= 14 && cx >= 0){
						if(map[cy][cx] == 2){
							cnt++;
						} else {
							break;
						}
					} else {
						break;
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}
				}
				if(cnt==4){
					System.out.println("흑돌이 이겼습니다.!");
					return;
				}// /대각선 확인 끝
			//  \ 대각선 확인
					cnt = 0;
					cx = bxy[0];
					cy = bxy[1];
					while(true){ // 좌측위 확인
						cy--;
						cx--;
						if(cy >= 0 && cx >= 0){
							if(map[cy][cx] == 2){
								cnt++;
							} else {
								break;
							}
						} else {
							break;
						}
						if(cnt==4){
							System.out.println("흑돌이 이겼습니다.!");
							return;
						}
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					} // 좌측위 확인 끝
					cx = bxy[0];
					cy = bxy[1];
					while(true){ // 우측아래 확인
						cy++;
						cx++;
						if(cy <= 14 && cx <= 14){
							if(map[cy][cx] == 2){
								cnt++;
							} else {
								break;
							}
						} else {
							break;
						}
						if(cnt==4){
							System.out.println("흑돌이 이겼습니다.!");
							return;
						}
					}
					if(cnt==4){
						System.out.println("흑돌이 이겼습니다.!");
						return;
					}// \대각선 확인 끝
	}
	private void whiteCheckWin() {
		// 좌우 확인
		int cnt = 0;
		int cx = wxy[0];
		int cy = wxy[1];
		while(true){ // 좌측 확인
			cx--;
			if(cx >= 0){
				if(map[wxy[1]][cx] == 1){
					cnt++;
				} else {
					break;
				}
			} else {
				break;
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}
		}
		cx = wxy[0];
		cy = wxy[1];
		while(true){ // 우측 확인
			cx++;
			if(cx <= 14){
				if(map[wxy[1]][cx] == 1){
					cnt++;
				} else {
					break;
				}
			} else {
				break;
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}
		}
		if(cnt==4){
			System.out.println("백돌이 이겼습니다.!");
			return;
		} // 좌우 확인 끝
		// 상하 확인
		cnt = 0;
		cx = wxy[0];
		cy = wxy[1];
		while(true){ // 위쪽 확인
			cy--;
			if(cy >= 0){
				if(map[cy][wxy[0]] == 1){
					cnt++;
				} else {
					break;
				}
			} else {
				break;
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}
		}
		cx = wxy[0];
		cy = wxy[1];
		while(true){ // 아래쪽 확인
			cy++;
			if(cy <= 14){
				if(map[cy][wxy[0]] == 1){
					cnt++;
				} else {
					break;
				}
			} else {
				break;
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}
		}
		if(cnt==4){
			System.out.println("백돌이 이겼습니다.!");
			return;
		} // 상하 확인 끝
		//  / 대각선 확인
		cnt = 0;
		cx = wxy[0];
		cy = wxy[1];
		while(true){ // 우측위 확인
			cy--;
			cx++;
			if(cy >= 0 && cx <= 14){
				if(map[cy][cx] == 1){
					cnt++;
				} else {
					break;
				}
			} else {
				break;
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}
		}
		if(cnt==4){
			System.out.println("백돌이 이겼습니다.!");
			return;
		} // 우측위 확인 끝
		cx = wxy[0];
		cy = wxy[1];
		while(true){ // 좌측아래 확인
			cy++;
			cx--;
			if(cy <= 14 && cx >= 0){
				if(map[cy][cx] == 1){
					cnt++;
				} else {
					break;
				}
			} else {
				break;
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}
		}
		if(cnt==4){
			System.out.println("백돌이 이겼습니다.!");
			return;
		}// /대각선 확인 끝
	//  \ 대각선 확인
			cnt = 0;
			cx = wxy[0];
			cy = wxy[1];
			while(true){ // 좌측위 확인
				cy--;
				cx--;
				if(cy >= 0 && cx >= 0){
					if(map[cy][cx] == 1){
						cnt++;
					} else {
						break;
					}
				} else {
					break;
				}
				if(cnt==4){
					System.out.println("백돌이 이겼습니다.!");
					return;
				}
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			} // 좌측위 확인 끝
			cx = wxy[0];
			cy = wxy[1];
			while(true){ // 우측아래 확인
				cy++;
				cx++;
				if(cy <= 14 && cx <= 14){
					if(map[cy][cx] == 1){
						cnt++;
					} else {
						break;
					}
				} else {
					break;
				}
				if(cnt==4){
					System.out.println("백돌이 이겼습니다.!");
					return;
				}
			}
			if(cnt==4){
				System.out.println("백돌이 이겼습니다.!");
				return;
			}// \대각선 확인 끝
	}
}

//우39 하40 좌37 상38
