package view;

import model.Inductee;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class InducteesTableModel extends DefaultTableModel
{
    private static final int NO_OF_COLS = 6;

    //Constants for column indices
    private static final int NAME_COL = 0;
    private static final int PHONE_COL = 1;
    private static final int JOB_TITLE_COL = 2;
    private static final int EMAIL_COL = 3;
    private static final int CAR_REG_COL = 4;
    private static final int FIRSTAID_COL = 5;

    private ArrayList<Inductee> dataModel;

    public InducteesTableModel(ArrayList<Inductee> dataModel)
    {
        super();
        this.dataModel = dataModel;
    }

    //This method is overridden from superclass DefaultTableModel
    public int getColumnCount()
    {
        return NO_OF_COLS;
    }
    //This method is overridden from superclass DefaultTableModel
    public String getColumnName(int columnIndex)
    {
        if(columnIndex == NAME_COL)
        {
            return "Name";
        }
        else if(columnIndex == PHONE_COL)
        {
            return "Phone Num";
        }
        else if(columnIndex == JOB_TITLE_COL)
        {
            return "Job Title";
        }
        else if(columnIndex == EMAIL_COL)
        {
            return "Email";
        }
        else if(columnIndex == CAR_REG_COL)
                {
                    return "Vehicle Reg";
                }
        else
        {
            return "First Aid Trained Y/N";
        }
    }

    //This method is overridden from superclass DefaultTableModel
    public int getRowCount()
    {
        if(this.dataModel != null)
        {
            return this.dataModel.size();
        }
        else
        {
            return 0;
        }
    }

    //This method is overridden from superclass DefaultTableModel
    //The row "points at" the Player and the col "points at" the
    //relevant piece of information from that Player.
    public Object getValueAt(int row, int col)
    {
        //Get the Player from the "raw data" ArrayList at
        //index row
        Inductee currentInductee = this.dataModel.get(row);
        if(col == NAME_COL)
        {
            return currentInductee.getName();
        }
        else if(col == PHONE_COL)
        {
            return currentInductee.getPhoneNum();
        }
        else if(col == JOB_TITLE_COL)
        {
            return currentInductee.getJobTitle();
        }
        else if(col == EMAIL_COL)
        {
            return currentInductee.getEmail();
        }
        else if(col == CAR_REG_COL)
        {
            return currentInductee.getCarReg();
        }
        else // It is the FirstAid column
        {
            return currentInductee.getFirstAidTrained();
        }
    }

    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }

}