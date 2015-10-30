import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import core.ArcadeMachine;

public class Runner {
	public static int numLevels;
	public static int playLevels;
	public static int chosenLevel;
	public static Random random;
	public static int chosenGame;
	public static ArrayList<String> games;
	public static String[] generators;
	public static boolean mouseClick;
	public static String firstGenerator;
	public static String secondGenerator;
	
	public static void main(String[] args){
		numLevels = 5;
		playLevels = 2;
		chosenGame = 0;
		generators = new String[]{"randomLevelGenerator", "constructiveLevelGenerator", "geneticLevelGenerator"};
		File[] files = new File("examples/games/").listFiles();
		games = new ArrayList<String>();
		for(File f:files){
			games.add(f.getName().substring(0, f.getName().lastIndexOf('.')));
		}
		random = new Random();
		mouseClick = false;
		
		ReasonFrame reasonFrame = new ReasonFrame();
		RunnerFrame frame = new RunnerFrame();
		SubmissionFrame pollFrame = new SubmissionFrame();
		
		reasonFrame.setVisible(true);
		reasonFrame.setFocusable(true);
		while(!mouseClick){
			System.out.print("");
		}
		mouseClick = false;
		reasonFrame.setVisible(false);
		
		while(true){
			chosenGame = (chosenGame + random.nextInt(games.size() - 1) + 1) % games.size();
			frame.setTitle(games.get(chosenGame));
			
			frame.tutorialLabel.setText(TutorialText.getTutorialText(games.get(chosenGame)));
			frame.startButton.setText("Play " + games.get(Runner.chosenGame) + " Tutorial Level");
			frame.pack();
			frame.setVisible(true);
			frame.setFocusable(true);
			while(!mouseClick){
				System.out.print("");
			}
			mouseClick = false;
			frame.setVisible(false);
			
			playGoodDesignGame();
			
			for(int i=0; i<playLevels; i++){
				String temp;
				if(i % 2 == 0){
					firstGenerator = Runner.generators[Runner.random.nextInt(Runner.generators.length)];
					temp = firstGenerator;
				}
				else{
					secondGenerator = Runner.generators[Runner.random.nextInt(Runner.generators.length)];
					temp = secondGenerator;
				}
				
				frame.tutorialLabel.setText(TutorialText.getTutorialText(games.get(chosenGame)));
				frame.startButton.setText("Play Generated Level");
				frame.pack();
				frame.setVisible(true);
				frame.setFocusable(true);
				while(!mouseClick){
					System.out.print("");
				}
				mouseClick = false;
				frame.setVisible(false);
				
				playRandomGame(temp);
				
				if(i % 2 == 1){
					pollFrame.resetRadio();
					pollFrame.setVisible(true);
					pollFrame.setFocusable(true);
					while(!mouseClick){
						System.out.print("");
					}
					mouseClick = false;
					pollFrame.setVisible(false);
				}
			}
		}
	}
	
	public static int getNumberOfFiles(String filePath, String fileName){
		File[] files = new File(filePath).listFiles();
		int number = 0;
		for(File f:files){
			if(f.getName().contains(fileName)){
				number += 1;
			}
		}
		
		return number;
	}
	
	public static void playGoodDesignGame(){
		chosenLevel = Runner.random.nextInt(Runner.numLevels);
		String gameFile = "examples/games/" + games.get(Runner.chosenGame) + ".txt";
		String levelFile = "examples/gridphysics/" + games.get(Runner.chosenGame) + "_lvl" + chosenLevel + ".txt";
		
		ArcadeMachine.playOneGame(gameFile, levelFile, null, Runner.random.nextInt(Integer.MAX_VALUE));
	}
	
	public static void playRandomGame(String chosenGenerator){
		chosenLevel = (chosenLevel + Runner.random.nextInt(Runner.numLevels - 1) + 1) % Runner.numLevels;
		int actionNumber = getNumberOfFiles("examples/actions/" + chosenGenerator + "/", games.get(Runner.chosenGame) + "_lvl" + chosenLevel);
		
		String gameFile = "examples/games/" + games.get(Runner.chosenGame) + ".txt";
		String levelFile = "examples/generatedLevels/" + chosenGenerator + "/" + games.get(Runner.chosenGame) + "_lvl" + chosenLevel + ".txt";
		String actionFile = "examples/actions/" + chosenGenerator + "/" + games.get(Runner.chosenGame) + "_lvl" + chosenLevel + "_" + actionNumber + ".txt";
		
		ArcadeMachine.playOneGeneratedLevel(gameFile, actionFile, levelFile, Runner.random.nextInt(Integer.MAX_VALUE));
	}
}
