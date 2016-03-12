using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Threading;
using System.Windows.Forms;

namespace ProgressBarExample
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void backgroundWorker1_DoWork(object sender, DoWorkEventArgs e)
        {
            for (int i = 1; i <= 100; i++)
            {
                if (!backgroundWorker1.CancellationPending)
                {
                    Thread.Sleep(100);
                    backgroundWorker1.ReportProgress(i);
                }
                else
                    e.Cancel = true;
            }
        }

        private void backgroundWorker1_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progressBar1.Value = e.ProgressPercentage;
            this.Text = e.ProgressPercentage.ToString();

        }

        private void startButton_Click(object sender, EventArgs e)
        {
            if (!backgroundWorker1.IsBusy)
            {
                label1.Text = "Running...";
                backgroundWorker1.RunWorkerAsync();
            }
            else
                MessageBox.Show("Can't run the process again until finished, please wait!");
        }

        private void cancelButton_Click(object sender, EventArgs e)
        {
            backgroundWorker1.CancelAsync();
        }

        private void backgroundWorker1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            label1.Text = "Completed!";
        }
    }
}