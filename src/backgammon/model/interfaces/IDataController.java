package backgammon.model.interfaces;

public interface IDataController extends IDataModel {
	
	public IBackgammonBoard getBackgammonBoard();
	public ICheckerList getCheckerListForIndex(int index) throws Exception;
}
