%{
@author Jason A Smith <jas7553>
%}
function [] = plot_lcs_data(filename)
    % Read the data in from the .csv file
    [numData, ~, ~] = xlsread(filename);
    stringSize = numData(:, 1);
    recursiveCalls = numData(:, 2);
    elapsedTime = numData(:, 3);

    % Create the figure
    fIm = figure('Name', filename, 'NumberTitle', 'off');
    set(fIm, 'Position', [400 400 900 400]);
    set(gcf, 'PaperPositionMode', 'auto');

    % Plot the Average Recursive Calls data
    subplot(1,2,1);
    scatter(stringSize, recursiveCalls, 36, [0 0 0], '*');
    hold on;
    plot(stringSize, recursiveCalls, 'k');
    xlim([stringSize(1) stringSize(length(stringSize))]);
    grid on;
    xlabel('String Length');
    ylabel('Average Recursive Calls');
    ylim([0 inf]);

    % Plot the Average Elapsed Time data
    subplot(1,2,2);
    scatter(stringSize, elapsedTime, 36, [0 0 0], '*');
    hold on;
    plot(stringSize, elapsedTime, 'k');
    xlim([stringSize(1) stringSize(length(stringSize))]);
    grid on;
    xlabel('String Length');
    ylabel('Average Elapased Time');
    ylim([0 inf]);

    % Write the figure to a file
    print(fIm, '-dpng', [strrep(filename, '.csv', ''), '.png']);
end