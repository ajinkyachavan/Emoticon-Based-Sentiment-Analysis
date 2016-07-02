

setwd("/home/ajinkya/workspace/EmoticonFuzzyCMeans/")

args <- commandArgs(trailingOnly = TRUE)

value1 <- args[1]
value2 <- args[2]

m <- args[3]
iter <- args[4]




#print(word)


mkdirs <- function(fp) {
  if(!file.exists(fp)) {
    mkdirs(dirname(fp))
    dir.create(fp)
  }
} 


plotData <- function(value1, value2, j, iter){
  
  
  d <- read.table(value1)
  
  d2 <- read.table(value2)
  
  my <- paste(value1,value2, sep="_")
  
  my <- as.expression(my)
  
  filename <- strsplit(toString(my),"/")
  
  filename <- filename[[1]][length(filename[[1]])]
  
  filename2 <- paste(toString(filename),iter, sep="_")
  
  
  mkdirs("/home/ajinkya/workspace/EmoticonFuzzyCMeans/negativePlots")
  
  mypath <- "/home/ajinkya/workspace/EmoticonFuzzyCMeans/negativePlots/"
  
  setwd(mypath)
  
  
  name <- paste(c("plot", j,filename2,".pdf"), collapse = "_")
  pdf(name)
  
  
  
  
  
  plot(d, col="blue", axes=FALSE, xlab = "Data Values")
  
  
  par(new = T)
  plot(d2, col="red", pch=4, cex=2, axes = FALSE)
  
  
  axis(side = 1)
  
  #plot(d,col=colr, type="p", main=filename, pch=1)
  
  
}



myPlot <- plotData(value1, value2, m, iter )
