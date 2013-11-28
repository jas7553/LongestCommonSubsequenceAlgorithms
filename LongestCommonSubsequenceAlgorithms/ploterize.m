%{
@author Jason A Smith <jas7553>
%}
files = dir(['results', filesep, '*.csv']);

for file = files'
    plot_lcs_data(['results', filesep, file.name]);
    close all
end